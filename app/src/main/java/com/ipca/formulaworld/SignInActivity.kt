package com.ipca.formulaworld

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ipca.formulaworld.utils.getSharedPreferences
import kotlinx.android.synthetic.main.activity_sign_in.*
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.ktx.auth

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var callbackManager: CallbackManager

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    companion object {
        private const val RC_SIGN_IN = 120
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        emailEditText = findViewById(R.id.signIn_emailEditText)
        passwordEditText = findViewById(R.id.signIn_passwordEditText)

        // Google Sign In

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = Firebase.auth

        google_login_btn.setOnClickListener {
            googleSignIn()
        }

        // Facebook Login

        // Initialize Facebook SDK
        FacebookSdk.sdkInitialize(this);

        // Facebook login callback

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()

        val buttonFacebookLogin = findViewById<LoginButton>(R.id.facebook_login_button)

        buttonFacebookLogin.setReadPermissions("email", "public_profile")
        buttonFacebookLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException) {
            }
        })
    }

    override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun signIn(view: View) {
        closeKeyboard()

        val email: String = emailEditText.text.toString().trim()
        val password: String = passwordEditText.text.toString().trim()

        if(email.isEmpty()) {
            emailEditText.error = "Email is required!"
            emailEditText.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = "The email is not valid!"
            emailEditText.requestFocus()
        }

        if(password.isEmpty()) {
            passwordEditText.error = "Password is required!"
            passwordEditText.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful) {

                val firestoreSetup = FirestoreSetup();
                val sp = getSharedPreferences(this.applicationContext)

                task.result.user?.uid?.let { uId ->

                    val firestoreDb = Firebase.firestore

                    firestoreDb.collection("users")
                        .whereEqualTo("uId", uId)
                        .get()
                        .addOnSuccessListener {
                            if(!it.isEmpty) {
                                val firstName = it.documents.first()["firstName"].toString()
                                val lastName = it.documents.first()["lastName"].toString()
                                val phone = it.documents.first()["phone"].toString()
                                val vat = it.documents.first()["vat"].toString()

                                Log.d("FirestoreUser", firstName)
                                // Store user data in Shared Preferences
                                sp.edit()
                                    .putString("uId", uId)
                                    .putString("email", email)
                                    .putString("firstName", firstName)
                                    .putString("lastName", lastName)
                                    .putString("phone", phone)
                                    .putString("vat", vat)
                                    .apply()

                                /* Consider using apply() instead; commit writes its data to persistent storage immediately,
                                whereas apply will handle it in the background */

                                Log.d("IntentUser", firstName)

                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                }
            } else {
                Toast.makeText(this, "Failed to login!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception

            if(task.isSuccessful) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("SignInFragment", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("SignInFragment", "Google sign in failed", e)
                }
            } else {
                Log.w("SignInFragment", exception.toString())
            }
        }

        // After register
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val message = data?.getStringExtra("message")
                if (message != null) {
                    Log.d("Resposta", message)
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignInFragment", "signInWithCredential:success")
//                    val user = mAuth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("SignInFragment", "signInWithCredential:failure")
                }
            }
    }

    fun register(view: View) {
        val intent = Intent(this@SignInActivity, RegisterActivity::class.java)
        startActivityForResult(intent, 1)
    }

    private fun closeKeyboard() {
        // this will give us the view
        // which is currently focus
        // in this layout
        val view = this.currentFocus

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {

            // now assign the system
            // service to InputMethodManager
            val manager: InputMethodManager = this.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            manager
                .hideSoftInputFromWindow(
                    view.windowToken, 0
                )
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    // Change UI according to user data.
    fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}