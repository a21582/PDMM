package com.ipca.formulaworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.ipca.formulaworld.model.User
import com.ipca.formulaworld.utils.getSharedPreferences

class ProfileActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var userId: String

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var vatEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: TextView
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val intent = intent
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val phone = intent.getStringExtra("phone")
        val vat = intent.getStringExtra("vat")
        val email = intent.getStringExtra("email")

        Log.d("UserProfile", email.toString())

        firstNameEditText = findViewById(R.id.profile_firstNameEditText)
        lastNameEditText = findViewById(R.id.profile_lastNameEditText)
        vatEditText = findViewById(R.id.profile_VatEditText)
        phoneEditText = findViewById(R.id.profile_phoneEditText)
        emailEditText = findViewById(R.id.profile_emailTextView)
        passwordEditText = findViewById(R.id.profile_passwordEditText)

        firstNameEditText.setText(firstName)
        lastNameEditText.setText(lastName)
        phoneEditText.setText(phone)
        vatEditText.setText(vat)
        emailEditText.text = email
    }
}