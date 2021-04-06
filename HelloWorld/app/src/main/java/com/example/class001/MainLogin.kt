package com.example.class001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)
    }

    fun btnCloseLogin(view: View) {
        finish()
    }
}