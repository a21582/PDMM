package com.ipca.formulaworld

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ipca.formulaworld.model.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var vatEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("users")

        firstNameEditText = findViewById(R.id.register_firstNameEditText)
        lastNameEditText = findViewById(R.id.register_lastNameEditText)
        vatEditText = findViewById(R.id.register_VatEditText)
        phoneEditText = findViewById(R.id.register_phoneEditText)
        emailEditText = findViewById(R.id.register_emailEditText)
        passwordEditText = findViewById(R.id.register_passwordEditText)
    }

    fun registerUser(view: View) {
        closeKeyboard()
        
        val firstName: String = firstNameEditText.text.toString().trim()
        val lastName: String = lastNameEditText.text.toString().trim()
        val vat: String = vatEditText.text.toString().trim()
        val phone: String = phoneEditText.text.toString().trim()
        val email: String = emailEditText.text.toString().trim()
        val password: String = passwordEditText.text.toString().trim()

        // Data validations

        if(firstName.isEmpty()) {
            firstNameEditText.error = "First name is required!"
            firstNameEditText.requestFocus()
            return
        }

        if(lastName.isEmpty()) {
            lastNameEditText.error = "Last name is required!"
            lastNameEditText.requestFocus()
            return
        }

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

        if(password.length < 6) {
            passwordEditText.error = "Password must be at least 6 characters!"
            passwordEditText.requestFocus()
            return
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
            this, OnCompleteListener<AuthResult>() { task ->
                if (task.isSuccessful) {

                    // Sign in success, update UI with the signed-in user's information
                    FirebaseAuth.getInstance().currentUser?.let {

                        val data = hashMapOf(
                            "uId" to it.uid,
                            "firstName" to firstName,
                            "lastName" to lastName,
                            "phone" to phone,
                            "vat" to vat
                        )

                        val firestoreDb = Firebase.firestore

                        // Add user to firestore collection
                        firestoreDb.collection("users").add(data).addOnSuccessListener {
                            val returnIntent = Intent()
                            returnIntent.putExtra("message", "User has been registered!")
                            setResult(Activity.RESULT_OK, returnIntent)
                            finish()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed to register!", Toast.LENGTH_LONG).show()
                        }
                    }

//                    val user: FirebaseUser? = mAuth.getCurrentUser();
//                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, task.exception?.localizedMessage.toString(), Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    private fun closeKeyboard() {
        // Get view currently in focus
        val view = this.currentFocus

        if (view != null) {

            val manager: InputMethodManager = this.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            manager
                .hideSoftInputFromWindow(
                    view.windowToken, 0
                )
        }
    }
}