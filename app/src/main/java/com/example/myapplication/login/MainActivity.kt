package com.example.myapplication.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.base.Hub
import com.example.myapplication.R


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
        val editor = getSharedPreferences("unique_name", MODE_PRIVATE).edit()
        val intent = Intent(this, Hub::class.java)
        when (findViewById<EditText>(R.id.editTextLogin).text.toString()){
            "user" -> {
                editor.putString("user_type", "user")
                editor.commit()
                startActivity(intent)
            }
            "admin" -> {
                editor.putString("user_type", "admin")
                editor.commit()
                startActivity(intent)
            }
            else -> {
                Toast.makeText(this,"Login invalido!",Toast.LENGTH_SHORT).show();
                findViewById<EditText>(R.id.editTextLogin).setText("")
                findViewById<EditText>(R.id.editTextTextPassword).setText("")
            }
        }


    }




}