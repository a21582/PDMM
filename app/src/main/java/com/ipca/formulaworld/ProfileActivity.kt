package com.ipca.formulaworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.ipca.formulaworld.model.User

class ProfileActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private lateinit var userId: String

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var vatEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("users")

        firstNameEditText = findViewById(R.id.profile_firstNameEditText)
        lastNameEditText = findViewById(R.id.profile_lastNameEditText)
        vatEditText = findViewById(R.id.profile_VatEditText)
        phoneEditText = findViewById(R.id.profile_phoneEditText)
        emailEditText = findViewById(R.id.profile_emailEditText)
        passwordEditText = findViewById(R.id.profile_passwordEditText)

//        user = FirebaseAuth.getInstance().currentUser!!
        val user = Firebase.auth.currentUser

        // Get user profile
        user?.let {
            Log.d("ProfileActivity", "Teste")
            Log.d("ProfileActivity", user.displayName.toString())

            userId = user.uid

            Log.d("ProfileActivity", userId)

            reference.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val userProfile = snapshot.getValue(User::class.java)

                    Log.d("ProfileActivity", userProfile.toString())

                    if(userProfile != null) {
                        Log.d("ProfileActivity", userProfile.email)

                        val firstName: String = userProfile.firstName
                        val lastName: String = userProfile.lastName
                        val vat: String = userProfile.vat
                        val phone: String = userProfile.phone
                        val email: String = userProfile.email

                        firstNameEditText.setText(firstName)
                        lastNameEditText.setText(lastName)
                        emailEditText.setText(email)
                    }

//                val children = snapshot!!.children
//                // This returns the correct child count...
//                println("count: "+snapshot.children.count().toString())
//                children.forEach {
//                    println(it.toString())
//                }
                }

                override fun onCancelled(error: DatabaseError) {
                    println(error!!.message)
                }
            })
        }
    }
}