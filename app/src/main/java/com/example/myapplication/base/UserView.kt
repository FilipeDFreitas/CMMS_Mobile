package com.example.myapplication.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.admin.ItemListAdmin
import com.example.myapplication.base.Base
import com.example.myapplication.conn.ApiClient
import com.example.myapplication.conn.ConnResponse
import com.example.myapplication.models.TipoDeleteAPI
import com.example.myapplication.models.User
import com.example.myapplication.models.UserAPI

class UserView : Base(){
    private lateinit var  adapterTipo : ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_admin)
        val prefs = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE)
        var usu :User

        val spinTipo = findViewById<Spinner>(R.id.spinnerTipoUser)
        val tipos = ArrayList<String>()
        tipos.add("Administrador")
        tipos.add("Usuário")
        adapterTipo = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
        spinTipo.adapter = adapterTipo

        if(prefs.getInt("userID",0)== intent.getSerializableExtra("id")) {
            findViewById<Spinner>(R.id.spinnerTipoUser).isEnabled = false
            findViewById<TextView>(R.id.buttonDeleteUser).visibility=View.INVISIBLE
        }
        if(intent.getSerializableExtra("new") != null){
            findViewById<TextView>(R.id.buttonDeleteUser).visibility=View.INVISIBLE
            findViewById<TextView>(R.id.userMail).isEnabled=true
        }
        else {
            // Implementacao campo tipo de user
            var id = 0

            if (intent.getSerializableExtra("id") != null) {
                id = intent.getSerializableExtra("id").toString().toInt()
            }
            val prefs = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE)

            ApiClient().getUser(
                prefs.getString("token", "")!!,
                id,
                object : ConnResponse<User> {
                    override fun success(t: User) {
                        findViewById<TextView>(R.id.userID).text = t.id.toString()
                        findViewById<TextView>(R.id.userName).text = t.name
                        findViewById<TextView>(R.id.userAddress).text = t.address
                        findViewById<TextView>(R.id.userCPF).text = t.document
                        findViewById<TextView>(R.id.userMail).text = t.email
                        findViewById<TextView>(R.id.userPhone).text = t.phone
                        spinTipo.setSelection(t.role)
                        usu = t
                    }

                    override fun fail() {
                        Toast.makeText(
                            this@UserView, "Falha na conexão com o servidor!",
                            Toast.LENGTH_SHORT
                        ).show();
                    }

                    override fun error(err: String) {
                        Toast.makeText(this@UserView, "Error: $err", Toast.LENGTH_SHORT).show();
                    }

                })

        }
    }

    fun saveUser(view: View){
        val prefs = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE)

        if(intent.getSerializableExtra("new") != null){
            ApiClient().postUser(prefs.getString("token", "")!!,
                User(
                    findViewById<TextView>(R.id.userID).text.toString().toInt(),
                    findViewById<TextView>(R.id.userName).text.toString(),
                    findViewById<TextView>(R.id.userPhone).text.toString(),
                    findViewById<TextView>(R.id.userCPF).text.toString(),
                    findViewById<TextView>(R.id.userAddress).text.toString(),
                    findViewById<TextView>(R.id.userMail).text.toString(),
                    findViewById<Spinner>(R.id.spinnerTipoUser).selectedItemPosition.toInt()
                ),
                object : ConnResponse<UserAPI> {
                    override fun success(t: UserAPI) {
                        Toast.makeText(
                            this@UserView, "Salvo com Sucesso!",
                            Toast.LENGTH_SHORT
                        ).show();
                        val intent = Intent(this@UserView, ItemListAdmin::class.java)
                        intent.putExtra("type","user")
                        startActivity(intent)
                    }

                    override fun fail() {
                        Toast.makeText(
                            this@UserView, "Preencha todos os dados corretamente!",
                            Toast.LENGTH_SHORT
                        ).show();
                    }

                    override fun error(err: String) {
                        Toast.makeText(this@UserView, "Error: $err", Toast.LENGTH_SHORT).show();
                    }

                })
        }
        else {
            ApiClient().putUser(prefs.getString("token", "")!!,
                User(
                    findViewById<TextView>(R.id.userID).text.toString().toInt(),
                    findViewById<TextView>(R.id.userName).text.toString(),
                    findViewById<TextView>(R.id.userPhone).text.toString(),
                    findViewById<TextView>(R.id.userCPF).text.toString(),
                    findViewById<TextView>(R.id.userAddress).text.toString(),
                    findViewById<TextView>(R.id.userMail).text.toString(),
                    findViewById<Spinner>(R.id.spinnerTipoUser).selectedItemPosition.toInt()
                ),
                object : ConnResponse<UserAPI> {
                    override fun success(t: UserAPI) {
                        Toast.makeText(
                            this@UserView, "Salvo com Sucesso!",
                            Toast.LENGTH_SHORT
                        ).show();
                    }

                    override fun fail() {
                        Toast.makeText(
                            this@UserView, "Falha na conexão com o servidor!",
                            Toast.LENGTH_SHORT
                        ).show();
                    }

                    override fun error(err: String) {
                        Toast.makeText(this@UserView, "Error: $err", Toast.LENGTH_SHORT).show();
                    }

                })
        }
    }

    fun deleteUser(view: View){
        val prefs = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE)
        ApiClient().deleteUser(prefs.getString("token","")!!,
            findViewById<TextView>(R.id.userID).text.toString().toInt()
            ,
            object : ConnResponse<TipoDeleteAPI> {
                override fun success(t: TipoDeleteAPI) {
                    Toast.makeText(this@UserView,"Removido com Sucesso!",
                        Toast.LENGTH_SHORT).show();
                    val intent = Intent(this@UserView, ItemListAdmin::class.java)
                    intent.putExtra("type","user")
                    startActivity(intent)
                }

                override fun fail() {
                    Toast.makeText(this@UserView,"Falha na conexão com o servidor!",
                        Toast.LENGTH_SHORT).show();
                }

                override fun error(err: String) {
                    Toast.makeText(this@UserView, "Error: $err", Toast.LENGTH_SHORT).show();
                }

            })

    }




}