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

    fun option(view: View) {
        val intent = Intent(this, ItemListAdmin::class.java)
        startActivity(intent)

    }

}