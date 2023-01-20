package com.example.myapplication.admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.base.Base

class Cadastros :  Base() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastros)
    }
    fun inc(view: View) {
        val intent = Intent(this, ItemListAdmin::class.java)
        intent.putExtra("type","inc")
        startActivity(intent)
    }
    fun equip(view: View) {
        val intent = Intent(this, ItemListAdmin::class.java)
        intent.putExtra("type","equip")
        startActivity(intent)
    }
    fun local(view: View) {
        val intent = Intent(this, ItemListAdmin::class.java)
        intent.putExtra("type","local")
        startActivity(intent)
    }
    fun user(view: View) {
        val intent = Intent(this, ItemListAdmin::class.java)
        intent.putExtra("type","user")
        startActivity(intent)
    }

}