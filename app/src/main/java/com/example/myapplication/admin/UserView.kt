package com.example.myapplication.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.myapplication.R
import com.example.myapplication.admin.Cadastros
import com.example.myapplication.base.Base
import com.example.myapplication.login.MainActivity

class UserView : Base(), AdapterView.OnItemSelectedListener {
    private lateinit var  adapterTipo : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_admin)

        // Implementacao campo TIPO

        val spinTipo = findViewById<Spinner>(R.id.spinnerTipoUser)
        val tipos = ArrayList<String>()
        tipos.add("Administrador")
        tipos.add("Usuário")
        adapterTipo = ArrayAdapter(this,android.R.layout.simple_spinner_item,tipos)
        spinTipo.adapter = adapterTipo
        spinTipo.onItemSelectedListener = this
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


}