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
import com.ipca.formulaworld.ui.bets.BetsFragment
import com.ipca.formulaworld.ui.categories.CategoriesFragment
import com.ipca.formulaworld.ui.home.HomeFragment
import com.ipca.formulaworld.ui.more.MoreFragment
import com.ipca.formulaworld.ui.store.StoreFragment

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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val user = auth.currentUser

        user?.email?.let { Log.d("User", it) }

        openHomeFragment()

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
        } else {
            Handler().postDelayed({
                // Google Sign In Tests
                val intent = Intent(this@MainActivity, SignInActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }
    }

    private fun openHomeFragment() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, HomeFragment())
        ft.commit()
    }

    private fun openCategoriesFragment() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, CategoriesFragment())
        ft.commit()
    }

    private fun openStoreFragment() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, StoreFragment())
        ft.commit()
    }

    private fun openBetsFragment() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, BetsFragment())
        ft.commit()
    }

    private fun openMoreFragment() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, MoreFragment())
        ft.commit()
    }
}