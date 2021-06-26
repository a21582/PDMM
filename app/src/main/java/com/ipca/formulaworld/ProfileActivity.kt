package com.ipca.formulaworld

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ipca.formulaworld.model.User
import com.ipca.formulaworld.utils.getSharedPreferences

class ProfileActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private var userId: String? = null

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var vatEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: TextView
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        userId = intent.getStringExtra("uId")

        if(userId.isNullOrEmpty()) {
            userId = Firebase.auth.uid

            if(userId.isNullOrEmpty()) {
                Toast.makeText(this, "Failed to get the user data!", Toast.LENGTH_LONG).show()
                finish()
            }
        }

        val intent = intent
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val phone = intent.getStringExtra("phone")
        val vat = intent.getStringExtra("vat")
        val email = intent.getStringExtra("email")

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

    fun updateProfile(view: View) {
        // Update user data in Firestore

        val firstName: String = firstNameEditText.text.toString().trim()
        val lastName: String = lastNameEditText.text.toString().trim()
        val vat: String = vatEditText.text.toString().trim()
        val phone: String = phoneEditText.text.toString().trim()

        val data = hashMapOf(
            "uId" to userId,
            "firstName" to firstName,
            "lastName" to lastName,
            "phone" to phone,
            "vat" to vat
        )

        val firestoreDb = Firebase.firestore

        firestoreDb.collection("users").document(userId.toString()).set(data).addOnSuccessListener {
            Toast.makeText(this, "User profile has been updated!", Toast.LENGTH_LONG).show()

            // Update Shared Preferences
            val sp = getSharedPreferences(this.applicationContext)
            sp.edit()
                .putString("uId", userId.toString())
                .putString("firstName", firstName)
                .putString("lastName", lastName)
                .putString("phone", phone)
                .putString("vat", vat)
                .apply()

            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to update user profile!", Toast.LENGTH_LONG).show()
        }
    }
}