package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View

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