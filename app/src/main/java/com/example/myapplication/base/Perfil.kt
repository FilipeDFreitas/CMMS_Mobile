package com.example.myapplication.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.R
import com.example.myapplication.admin.Cadastros
import com.example.myapplication.login.MainActivity

class Perfil : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val prefs = getSharedPreferences("unique_name", MODE_PRIVATE)
        when (prefs.getString("user_type","")){
            "user"-> menuInflater.inflate(R.menu.menu_user,menu)
            "admin" -> menuInflater.inflate(R.menu.menu_admin,menu)
            "" -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.inc_choice ->{
                val intent = Intent(this, Hub::class.java)
                startActivity(intent)
            }
            R.id.cad_choice ->{
                val intent = Intent(this, Cadastros::class.java)
                startActivity(intent)
            }
            R.id.perfil_choice -> {}
            R.id.logout ->{
                val editor = getSharedPreferences("unique_name", MODE_PRIVATE).edit()
                editor.putString("user_type", "")
                editor.commit()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}