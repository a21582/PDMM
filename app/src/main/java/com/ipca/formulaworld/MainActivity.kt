package com.ipca.formulaworld

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.util.TypedValue
import android.view.Gravity
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.fragment.app.FragmentTransaction
import androidx.room.Room
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.google.firebase.ktx.Firebase
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.ui.bets.BetsCompetitionFragment
import com.ipca.formulaworld.ui.categories.CategoriesFragment
import com.ipca.formulaworld.ui.more.MoreFragment
import com.ipca.formulaworld.ui.news.NewsFragment
import com.ipca.formulaworld.utils.isNetworkAvailable

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

        if(isNetworkAvailable(this)) {
            val firestoreSetup = FirestoreSetup(db);
            firestoreSetup.syncAll(db)
        }

        auth = Firebase.auth

        openNewsFragment()
    }

    override fun onBackPressed() {
        Log.d("BackButton", "Teste")
        super.onBackPressed()
    }

    override fun onStart() {
        super.onStart()

        openNewsFragment()

        navigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_home -> {
                    openNewsFragment()
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

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(
            this
        ) { instanceIdResult: InstanceIdResult ->
            val newToken = instanceIdResult.token
            Log.e("newToken", newToken)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            Log.d("BackButton", "Teste2")
            supportFragmentManager.popBackStack()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun openNewsFragment() {
        navigationView.menu.findItem(R.id.navigation_home).isChecked = true

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, NewsFragment())
        ft.commit()
    }

    private fun openCategoriesFragment() {
        navigationView.menu.findItem(R.id.navigation_categories).isChecked = true

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, CategoriesFragment())
        ft.commit()
    }

    private fun openBetsFragment() {
        navigationView.menu.findItem(R.id.navigation_bets).isChecked = true

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, BetsCompetitionFragment())
        ft.commit()
    }

    private fun openMoreFragment() {
        navigationView.menu.findItem(R.id.navigation_more).isChecked = true

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, MoreFragment())
        ft.commit()
    }

    private fun openUserFragment() {
        navigationView.menu.findItem(R.id.navigation_user).isChecked = true

        // If the user is logged in, show profile
        // Otherwise, show login page

        val currentUser = auth.currentUser

        if (currentUser != null) {

            val intent = Intent(this, ProfileActivity::class.java)

            val sp = com.ipca.formulaworld.utils.getSharedPreferences(applicationContext)

            val firstName = sp.getString("firstName", "")
            val lastName = sp.getString("lastName", "")
            val phone = sp.getString("phone", "")
            val vat = sp.getString("vat", "")
            val email = sp.getString("email", "")

            intent.putExtra("firstName", firstName)
            intent.putExtra("lastName", lastName)
            intent.putExtra("phone", phone)
            intent.putExtra("vat", vat)
            intent.putExtra("email", email)

            startActivity(intent)
        } else {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}
