package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun forgotPassword(view: View) {
        val intent = Intent(this, Forgot::class.java)
        startActivity(intent)

    }
    fun hub(view: View) {
        val intent = Intent(this, Hub::class.java)
        startActivity(intent)

    }

}