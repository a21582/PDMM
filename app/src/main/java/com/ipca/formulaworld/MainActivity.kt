package com.ipca.formulaworld

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val user = auth.currentUser

        Handler().postDelayed({
            if(user != null) {
                val navView: BottomNavigationView = findViewById(R.id.nav_view)

                val navController = findNavController(R.id.nav_host_fragment)
                // Passing each menu ID as a set of Ids because each
                // menu should be considered as top level destinations.
                val appBarConfiguration = AppBarConfiguration(setOf(
                    R.id.navigation_home, R.id.navigation_categories, R.id.navigation_store, R.id.navigation_bets, R.id.navigation_more))
                setupActionBarWithNavController(navController, appBarConfiguration)
                navView.setupWithNavController(navController)

                val signOutButton = findViewById<Button>(R.id.google_logout_btn)

                signOutButton.setOnClickListener {
                    // Sign out from GoogleSignInClient, so the user can sign in with a different Google account
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(this.getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build()

                    val googleSignInClient = GoogleSignIn.getClient(this, gso)

                    googleSignInClient.signOut()

                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                val profileButton = findViewById<Button>(R.id.profile_button)

                profileButton.setOnClickListener {
                    // Open user profile's page

                    val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {
                // Google Sign In Tests
                val intent = Intent(this@MainActivity, SignInActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)
    }
}