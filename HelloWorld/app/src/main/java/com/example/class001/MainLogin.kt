package com.example.class001

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)

        val main_email = intent.getStringExtra("email")
        val get_email = this.findViewById<EditText>(R.id.reciveEmailText)
        get_email.setText(main_email)
    }

    fun btnCloseLogin(view: View) {
        val returnItentent = Intent()
        val get_email = this.findViewById<EditText>(R.id.reciveEmailText)
        returnItentent.putExtra("returnEmail", get_email.text.toString())
        setResult(Activity.RESULT_OK,  returnItentent)
        finish()
    }
}