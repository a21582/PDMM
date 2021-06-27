package com.ipca.formulaworld

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.ui.bets.BetsCompetitionFragment
import com.ipca.formulaworld.ui.categories.CategoriesFragment
import com.ipca.formulaworld.ui.more.MoreFragment
import com.ipca.formulaworld.ui.news.NewsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navigationView: BottomNavigationView

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java, "formulaworld.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_FormulaWorld)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView = findViewById(R.id.nav_view)
        // Firestore

        val firestoreSetup = FirestoreSetup(db);
        firestoreSetup.syncAll(db)

        auth = Firebase.auth

        openHomeFragment()
    }

    override fun onStart() {
        super.onStart()

        openHomeFragment()
        // Check if the user is signed in
        val user = auth.currentUser

//        if (user != null) {
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
                    R.id.navigation_bets -> {
                        openBetsFragment()
                        true
                    }
                    R.id.navigation_more -> {
                        openMoreFragment()
                        true
                    }
                    R.id.navigation_user -> {
                        openUserFragment()
                        true
                    }
                    else -> super.onOptionsItemSelected(item)
                }
            }
//        } else {
//                // Redirect to SignIn
//                val intent = Intent(this@MainActivity, SignInActivity::class.java)
//                startActivity(intent)
//                finish()
//        }
        }


    }

    private fun openHomeFragment() {
        navigationView.menu.findItem(R.id.navigation_home).isChecked = true

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, NewsFragment()).addToBackStack(null)
        ft.commit()
    }

    private fun openCategoriesFragment() {
        navigationView.menu.findItem(R.id.navigation_categories).isChecked = true

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, CategoriesFragment()).addToBackStack(null)
        ft.commit()
    }

    private fun openBetsFragment() {
        navigationView.menu.findItem(R.id.navigation_bets).isChecked = true

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, BetsCompetitionFragment()).addToBackStack(null)
        ft.commit()
    }

    private fun openMoreFragment() {
        navigationView.menu.findItem(R.id.navigation_more).isChecked = true

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, MoreFragment()).addToBackStack(null)
        ft.commit()
    }

    private fun openUserFragment() {
        navigationView.menu.findItem(R.id.navigation_user).isChecked = true

        // If the user is logged in, show profile
        // Otherwise, show login page

        val currentUser = auth.currentUser

        if (currentUser != null) {
            startActivity(Intent(this, ProfileActivity::class.java))
        } else {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}