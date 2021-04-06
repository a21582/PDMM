package com.example.class001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun Teste_fun(view: View) {
        val intent = Intent(this@MainActivity, MainLogin::class.java )
        startActivity(intent)
    }
}