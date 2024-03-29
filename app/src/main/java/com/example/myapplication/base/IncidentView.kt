package com.example.myapplication.base

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.models.Incident
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class IncidentView : Base(), AdapterView.OnItemSelectedListener{
    private lateinit var  adapterTipo : ArrayAdapter<String>
    private lateinit var  adapterEquipamento : ArrayAdapter<String>
    private lateinit var  adapterLocal : ArrayAdapter<String>
    var c : Calendar = Calendar.getInstance()
    private var df : SimpleDateFormat? = null



    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.getSerializableExtra("inc") != null) {
            setContentView(R.layout.activity_incident_view)



            //comentários

            // getting the recyclerview by its id
            val recyclerComments =
                findViewById<RecyclerView>(R.id.recycler_comments)

            // this creates a vertical layout Manager
            recyclerComments.layoutManager = LinearLayoutManager(this)

            // ArrayList of class ItemsViewModel
            val data = ArrayList<Incident>()

            // This loop will create 10 Views containing
            // the image with the count of view
            for (i: Int in 1..2) {
                if (i % 2 == 0)
                    data.add(0,
                        Incident("[Admin]",
                            "10-12-2020",
                            "Substituicão",
                            "A07 - Térreo - SEPT",
                            "Lampada",
                            "Verificando problema",
                            "31-12-2018")
                    )
                else
                    data.add(0,
                        Incident("[Usuário]",
                            "05-12-2020",
                            "Quebra",
                            "Banheiro - Primeiro andar - SEPT",
                            "Pia",
                            "Problema encontrado no local xxxxx onde não consigo mais utilizar a internet por alguma razão",
                            "31-12-2022")
                    )
            }

            // This will pass the ArrayList to our Adapter
            val adapter = CommentAdapter(data)

            // Setting the Adapter with the recyclerview
            recyclerComments.adapter = adapter

            adapter.setOnItemClickListener(object : CommentAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {

                }
            })

            val incident = intent.getSerializableExtra("inc") as Incident
            findViewById<TextView>(R.id.numID).text = incident.id
            findViewById<TextView>(R.id.tituloNome).text = incident.titulo
            findViewById<TextView>(R.id.tituloNome).isEnabled = false
            findViewById<TextView>(R.id.dataShow).text = incident.data

        }else{
            setContentView(R.layout.activity_new_incident_view)

        }
        df = SimpleDateFormat("dd/MM/yyyy")
        val formattedDate = df!!.format(c.time)

        findViewById<TextView>(R.id.tituloNome).isEnabled = true
        findViewById<TextView>(R.id.dataShow).text = "$formattedDate"

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