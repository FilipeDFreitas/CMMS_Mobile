package com.example.myapplication.admin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import com.example.myapplication.R
import com.example.myapplication.base.Base
import java.text.SimpleDateFormat
import java.util.*


class ItemView : Base(){
    private lateinit var  adapterTipo : ArrayAdapter<String>
    private lateinit var  adapterEquipamento : ArrayAdapter<String>
    private lateinit var  adapterLocal : ArrayAdapter<String>
    var c : Calendar = Calendar.getInstance()
    private var df : SimpleDateFormat? = null



    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_item_view)



        val type = intent.getSerializableExtra("type").toString()
        if(intent.getSerializableExtra("id") != null) {
            val nome = intent.getSerializableExtra("nome").toString()
            val id = intent.getSerializableExtra("id").toString()
            val desc = intent.getSerializableExtra("desc").toString()

            findViewById<TextView>(R.id.tituloNome).text = nome
            findViewById<TextView>(R.id.tituloDesc).text = desc
            findViewById<TextView>(R.id.numID).text = id

        }


            if (type == "equip")
                findViewById<TextView>(R.id.buttonCreate).text = "Salvar Equipamento"
            else if (type == "local")
                findViewById<TextView>(R.id.buttonCreate).text = "Salvar Local"
            else if (type == "inc")
                findViewById<TextView>(R.id.buttonCreate).text = "Salvar Incidente"

    }





}