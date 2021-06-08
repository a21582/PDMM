package com.ipca.formulaworld

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ipca.formulaworld.ui.bets.BetsFragment
import com.ipca.formulaworld.ui.categories.CategoriesFragment
import com.ipca.formulaworld.ui.home.HomeFragment
import com.ipca.formulaworld.ui.more.MoreFragment
import com.ipca.formulaworld.ui.store.StoreFragment

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val user = auth.currentUser

//        openHomeFragment()
//
//        openCategoriesFragment()

        Handler().postDelayed({
            if (user != null) {
                navigationView = findViewById(R.id.nav_view)
                navigationView.setOnNavigationItemSelectedListener { item ->
                    when(item.itemId) {
                        R.id.navigation_home -> {
                            openHomeFragment()
                            true
                        }
                        R.id.navigation_categories -> {
                            openCategoriesFragment()
                            true
                        }
                        R.id.navigation_store -> {
                            openStoreFragment()
                            true
                        }
                        R.id.navigation_bets -> {
                            openBetsFragment()
                            true
                        }
                        R.id.navigation_more -> {
                            openMoreFragment()
                            true
                        }
                        else -> super.onOptionsItemSelected(item)
                    }
                }

//                val navController = findNavController(R.id.nav_host_fragment)

                // Passing each menu ID as a set of Ids because each
                // menu should be considered as top level destinations.
//                val appBarConfiguration = AppBarConfiguration(setOf(
//                    R.id.navigation_home, R.id.navigation_categories, R.id.navigation_store, R.id.navigation_bets, R.id.navigation_more))
//
//                setupActionBarWithNavController(navController, appBarConfiguration)
//                navView.setupWithNavController(navController)

//                val signOutButton = findViewById<Button>(R.id.google_logout_btn)
//
//                signOutButton.setOnClickListener {
//                    // Sign out from GoogleSignInClient, so the user can sign in with a different Google account
//                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                        .requestIdToken(this.getString(R.string.default_web_client_id))
//                        .requestEmail()
//                        .build()
//
//                    val googleSignInClient = GoogleSignIn.getClient(this, gso)
//
//                    googleSignInClient.signOut()
//
//                    val intent = Intent(this, SignInActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//
//                val profileButton = findViewById<Button>(R.id.profile_button)
//
//                profileButton.setOnClickListener {
//                    // Open user profile's page
//
//                    val intent = Intent(this@MainActivity, ProfileActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
            } else {
                // Google Sign In Tests
                val intent = Intent(this@MainActivity, SignInActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)
    }

    fun test(view: View) {
        var fragment: Fragment = BetsFragment()
        Log.d("StoreFragment", "Teste click")
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.nav_host_fragment, fragment)
//        transaction.commit()
    }

    fun openHomeFragment() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, HomeFragment())
        ft.commit()
    }

    fun openCategoriesFragment() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, CategoriesFragment())
        ft.commit()
    }

    fun openStoreFragment() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, StoreFragment())
        ft.commit()
    }

    fun openBetsFragment() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, BetsFragment())
        ft.commit()
    }

    fun openMoreFragment() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, MoreFragment())
        ft.commit()
    }
}