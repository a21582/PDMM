package com.example.class001

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val email = this.findViewById<EditText>(R.id.editTextTextEmailAddress)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                val email = this.findViewById<EditText>(R.id.editTextTextEmailAddress)
                email.setText(data?.getStringExtra("returnEmail"))
            }
        }
    }

    fun Teste_fun(view: View) {
        val intent = Intent(this@MainActivity, MainLogin::class.java )
        val email = this.findViewById<EditText>(R.id.editTextTextEmailAddress)
        intent.putExtra("email", email.text.toString())
        startActivityForResult(intent, 1)
    }
}