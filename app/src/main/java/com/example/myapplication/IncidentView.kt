package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class IncidentView : AppCompatActivity(), AdapterView.OnItemSelectedListener{
    private lateinit var  adapterTipo : ArrayAdapter<String>
    private lateinit var  adapterEquipamento : ArrayAdapter<String>
    private lateinit var  adapterLocal : ArrayAdapter<String>

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incident_view)

        val incident = intent.getSerializableExtra("inc") as Incident
        findViewById<TextView>(R.id.incShow).text = incident.id
        findViewById<TextView>(R.id.tituloShow).text = incident.titulo
        findViewById<TextView>(R.id.tituloShow).isEnabled = false
        findViewById<TextView>(R.id.dataShow).text = incident.data

        // Implementacao campo TIPO

        val spinTipo = findViewById<Spinner>(R.id.spinnerTipo)
        val tipos = ArrayList<String>()
        tipos.add("---")
        tipos.add("Adicionar tipo")
        adapterTipo = ArrayAdapter(this,android.R.layout.simple_spinner_item,tipos)
        spinTipo.adapter = adapterTipo
        spinTipo.onItemSelectedListener = this

        // Implementacao campo EQUIPAMENTO

        val spinEquipamento = findViewById<Spinner>(R.id.spinnerEquipamento)
        val equipamentos = ArrayList<String>()
        equipamentos.add("---")
        equipamentos.add("Adicionar equipamento")
        adapterEquipamento = ArrayAdapter(this,android.R.layout.simple_spinner_item,equipamentos)
        spinEquipamento.adapter = adapterEquipamento
        spinEquipamento.onItemSelectedListener = this

        // Implementacao campo LOCAL

        val spinLocal = findViewById<Spinner>(R.id.spinnerLocal)
        val locais = ArrayList<String>()
        locais.add("---")
        locais.add("Adicionar local")
        adapterLocal = ArrayAdapter(this,android.R.layout.simple_spinner_item,locais)
        spinLocal.adapter = adapterLocal
        spinLocal.onItemSelectedListener = this
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        if (p2 == 1){
            val novoItem = EditText(this)
            val inputDialog = AlertDialog.Builder(this)
            inputDialog.setTitle("Digite o item")
            inputDialog.setView(novoItem)
            inputDialog.setPositiveButton("OK"
            ) { dialogInterface, i ->
                if (p0 != null) {
                    if (p0.getItemAtPosition(p2).toString() == "Adicionar tipo")
                        adapterTipo.add(novoItem.text.toString())
                    else if (p0.getItemAtPosition(p2).toString() == "Adicionar equipamento")
                        adapterEquipamento.add(novoItem.text.toString())
                    else
                        adapterLocal.add(novoItem.text.toString())
                }
            }
            inputDialog.setNegativeButton("Voltar",null)
            inputDialog.create()
            inputDialog.show()

        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}