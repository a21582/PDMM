package com.ipca.formulaworld.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ipca.formulaworld.ProfileActivity
import com.ipca.formulaworld.R
import com.ipca.formulaworld.SignInActivity
import com.ipca.formulaworld.utils.getSharedPreferences

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val signOutButton = view.findViewById<Button>(R.id.google_logout_btn)

        signOutButton.setOnClickListener {
            Log.d("Logout", "Logout")

            Firebase.auth.signOut()

            // Sign out from GoogleSignInClient, so the user can sign in with a different Google account
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(this.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(view.context, gso)
            googleSignInClient.signOut()

            val intent = Intent(activity, SignInActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
//            finish()
        }

        val profileButton = view.findViewById<Button>(R.id.profile_button)

        profileButton.setOnClickListener {
            // Open user profile's page

            val intent = Intent(activity, ProfileActivity::class.java)

            activity?.let { it1 ->
                val sp = getSharedPreferences(it1.applicationContext)

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
            }

            startActivity(intent)
        }
    }
}