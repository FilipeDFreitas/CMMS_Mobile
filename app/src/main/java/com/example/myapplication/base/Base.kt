package com.example.myapplication.base

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.*
import com.example.myapplication.admin.Cadastros
import com.example.myapplication.conn.ApiClient
import com.example.myapplication.conn.ConnResponse
import com.example.myapplication.login.MainActivity
import com.example.myapplication.models.User

open class Base: AppCompatActivity(){
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val prefs = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE)

        when (prefs.getString("user_type","")){
            "user"-> menuInflater.inflate(R.menu.menu_user,menu)
            "admin" -> menuInflater.inflate(R.menu.menu_admin,menu)
            "" -> {
                if (prefs.getString("token","")!="")
                    ApiClient().getUser(
                    prefs.getString("token","")!!,
                    prefs.getInt("userID",0)!!,
                    object : ConnResponse<User> {
                        override fun success(t: User) {
                            if(t.role == 0) {
                                prefs.edit().putString("user_type", "admin")
                                menuInflater.inflate(R.menu.menu_admin, menu)
                            }
                            else {
                                prefs.edit().putString("user_type", "user")
                                menuInflater.inflate(R.menu.menu_user,menu)
                            }
                            prefs.edit().commit()
                        }

                        override fun fail() {
                            Toast.makeText(this@Base,"Falha na conexão com o servidor!",
                                Toast.LENGTH_SHORT).show();
                        }

                        override fun error(err: String) {
                            Toast.makeText(this@Base, "Error: $err", Toast.LENGTH_SHORT).show();
                        }

                    })
                else{
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }


            }
        }


        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val prefs = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE)
        when (item.itemId){
            R.id.inc_choice ->{
                val intent = Intent(this, Hub::class.java)
                startActivity(intent)
            }
            R.id.cad_choice ->{
                val intent = Intent(this, Cadastros::class.java)
                startActivity(intent)
            }
            R.id.perfil_choice -> {
                val intent = Intent(this, UserView::class.java)
                intent.putExtra("id",prefs.getInt("userID",0))
                startActivity(intent)
            }
            R.id.logout ->{
                val editor = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE).edit()
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