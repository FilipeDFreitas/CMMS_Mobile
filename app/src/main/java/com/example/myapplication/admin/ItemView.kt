package com.example.myapplication.admin

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.base.Base
import com.example.myapplication.conn.ApiClient
import com.example.myapplication.conn.ConnResponse
import com.example.myapplication.models.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ItemView : Base(), AdapterView.OnItemSelectedListener{
    private lateinit var  adapterLocal : ArrayAdapter<String>
    var c : Calendar = Calendar.getInstance()
    private var df : SimpleDateFormat? = null
    lateinit var type :String
    lateinit var sortedList: List<Tipo>


    @SuppressLint("CutPasteId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_item_view)



        type = intent.getSerializableExtra("type").toString()
        val prefs = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE)
        if(intent.getSerializableExtra("id") != null) {

            val nome = intent.getSerializableExtra("nome").toString()
            val id = intent.getSerializableExtra("id").toString()
            val desc = intent.getSerializableExtra("desc").toString()
            findViewById<TextView>(R.id.tituloNome).text = nome
            findViewById<TextView>(R.id.tituloDesc).text = desc
            findViewById<TextView>(R.id.numID).text = id




        }
        if(intent.getSerializableExtra("new") != null)
            findViewById<TextView>(R.id.buttonDelete).visibility=View.INVISIBLE
        else {
            findViewById<TextView>(R.id.buttonDelete).visibility = View.VISIBLE
            when (type) {
                "equip" -> {
                    spinlocalrefresh()
                    //buscar resto da informação de equip
                    ApiClient().getEquip(prefs.getString("token", "")!!,
                        intent.getSerializableExtra("id").toString().toInt(),
                        object : ConnResponse<ItemAPI> {
                            override fun success(t: ItemAPI) {

                                for((index,value) in sortedList.withIndex()){
                                    if (value.id ==t.entity.locationId)
                                        findViewById<Spinner>(R.id.localSpinner).setSelection(index+2)
                                }

                            }

                            override fun fail() {
                                Toast.makeText(
                                    this@ItemView, "Falha na conexão com o servidor!",
                                    Toast.LENGTH_SHORT
                                ).show();
                            }

                            override fun error(err: String) {
                                Toast.makeText(this@ItemView, "Error: $err", Toast.LENGTH_SHORT).show();
                            }

                        })
                }
            }
        }

        when (type) {
            "equip" -> {


                findViewById<TextView>(R.id.buttonCreate).text = "Salvar Equipamento"
                findViewById<TextView>(R.id.dataTextLocal).visibility=View.VISIBLE
                findViewById<Spinner>(R.id.localSpinner).visibility=View.VISIBLE

                spinlocalrefresh()
            }
            "local" -> {
                findViewById<TextView>(R.id.buttonCreate).text = "Salvar Local"
                findViewById<TextView>(R.id.dataTextLocal).visibility=View.INVISIBLE
                findViewById<Spinner>(R.id.localSpinner).visibility=View.INVISIBLE
            }
            "inc" -> {
                findViewById<TextView>(R.id.buttonCreate).text = "Salvar Tipo"
                findViewById<TextView>(R.id.dataTextLocal).visibility=View.INVISIBLE
                findViewById<Spinner>(R.id.localSpinner).visibility=View.INVISIBLE
            }
        }

    }
    fun executeButton(view: View) {
        val prefs = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE)
        val nome = findViewById<TextView>(R.id.tituloNome).text.toString()
        val desc =findViewById<TextView>(R.id.tituloDesc).text.toString()
        var localID =0
        val new = intent.getSerializableExtra("new")

        if (new !=null) {

            when (type) {
                "equip" -> {


                    ApiClient().postEquip(prefs.getString("token", "")!!,
                        Item(0, nome, desc,localID),
                        object : ConnResponse<ItemList> {
                            override fun success(t: ItemList) {
                                val intent = Intent(this@ItemView, ItemListAdmin::class.java)
                                intent.putExtra("type", "equip")
                                startActivity(intent)
                            }

                            override fun fail() {
                                Toast.makeText(
                                    this@ItemView, "Falha na conexão com o servidor!",
                                    Toast.LENGTH_SHORT
                                ).show();
                            }

                            override fun error(err: String) {
                                Toast.makeText(this@ItemView, "Error: $err", Toast.LENGTH_SHORT).show();
                            }

                        })
                }
                "local" -> {
                    ApiClient().postLocal(prefs.getString("token", "")!!,
                        Local(0, nome, desc),
                        object : ConnResponse<LocalAPI> {
                            override fun success(t: LocalAPI) {
                                val intent = Intent(this@ItemView, ItemListAdmin::class.java)
                                intent.putExtra("type", "local")
                                startActivity(intent)
                            }

                            override fun fail() {
                                Toast.makeText(
                                    this@ItemView, "Falha na conexão com o servidor!",
                                    Toast.LENGTH_SHORT
                                ).show();
                            }

                            override fun error(err: String) {
                                Toast.makeText(this@ItemView, "Error: $err", Toast.LENGTH_SHORT).show();
                            }

                        })

                }
                "inc" -> {
                    ApiClient().postTipo(prefs.getString("token", "")!!,
                        Tipo(0, nome, desc),
                        object : ConnResponse<TipoAPI> {
                            override fun success(t: TipoAPI) {
                                val intent = Intent(this@ItemView, ItemListAdmin::class.java)
                                intent.putExtra("type", "inc")
                                startActivity(intent)
                            }

                            override fun fail() {
                                Toast.makeText(
                                    this@ItemView, "Falha na conexão com o servidor!",
                                    Toast.LENGTH_SHORT
                                ).show();
                            }

                            override fun error(err: String) {
                                Toast.makeText(this@ItemView, "Error: $err", Toast.LENGTH_SHORT).show();
                            }

                        })

                }
            }
        }
        else{
            val id =findViewById<TextView>(R.id.numID).text.toString().toInt()
            when (type) {
                "equip" -> {
                    for(local in sortedList){
                        if(local.name == findViewById<Spinner>(R.id.localSpinner).selectedItem.toString())
                            localID= local.id
                    }
                    ApiClient().putEquip(prefs.getString("token", "")!!,
                        Item(id, nome, desc, localID),
                        object : ConnResponse<ItemList> {
                            override fun success(t: ItemList) {
                                val intent = Intent(this@ItemView, ItemListAdmin::class.java)
                                intent.putExtra("type", "equip")
                                startActivity(intent)
                            }

                            override fun fail() {
                                Toast.makeText(
                                    this@ItemView, "Falha na conexão com o servidor!",
                                    Toast.LENGTH_SHORT
                                ).show();
                            }

                            override fun error(err: String) {
                                Toast.makeText(this@ItemView, "Error: $err", Toast.LENGTH_SHORT).show();
                            }

                        })
                }
                "local" -> {
                    ApiClient().putLocal(prefs.getString("token", "")!!,
                        Local(id, nome, desc),
                        object : ConnResponse<LocalAPI> {
                            override fun success(t: LocalAPI) {
                                val intent = Intent(this@ItemView, ItemListAdmin::class.java)
                                intent.putExtra("type", "local")
                                startActivity(intent)
                            }

                            override fun fail() {
                                Toast.makeText(
                                    this@ItemView, "Falha na conexão com o servidor!",
                                    Toast.LENGTH_SHORT
                                ).show();
                            }

                            override fun error(err: String) {
                                Toast.makeText(this@ItemView, "Error: $err", Toast.LENGTH_SHORT).show();
                            }

                        })

                }
                "inc" -> {

                    ApiClient().putTipo(prefs.getString("token","")!!,
                        Tipo(id,nome,desc),
                        object : ConnResponse<TipoAPI> {
                            override fun success(t: TipoAPI) {
                                val intent = Intent(this@ItemView, ItemListAdmin::class.java)
                                intent.putExtra("type","inc")
                                startActivity(intent)
                            }

                            override fun fail() {
                                Toast.makeText(this@ItemView,"Falha na conexão com o servidor!",
                                    Toast.LENGTH_SHORT).show();
                            }

                            override fun error(err: String) {
                                Toast.makeText(this@ItemView, "Error: $err", Toast.LENGTH_SHORT).show();
                            }

                        })
                }
            }
        }





    }
    fun deleteButton(view: View) {

        val prefs = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE)


            val id =findViewById<TextView>(R.id.numID).text.toString().toInt()
            if (type == "equip"){
                ApiClient().deleteEquip(prefs.getString("token","")!!,
                    id,
                    object : ConnResponse<ItemDeleteAPI> {
                        override fun success(t: ItemDeleteAPI) {
                            val intent = Intent(this@ItemView, ItemListAdmin::class.java)
                            intent.putExtra("type","equip")
                            startActivity(intent)
                        }

                        override fun fail() {
                            Toast.makeText(this@ItemView,"Falha na conexão com o servidor!",
                                Toast.LENGTH_SHORT).show();
                        }

                        override fun error(err: String) {
                            Toast.makeText(this@ItemView, "Error: $err", Toast.LENGTH_SHORT).show();
                        }

                    })
            }
            else if (type == "local"){
                ApiClient().deleteLocal(prefs.getString("token","")!!,
                    id,
                    object : ConnResponse<LocalDeleteAPI> {
                        override fun success(t: LocalDeleteAPI) {
                            val intent = Intent(this@ItemView, ItemListAdmin::class.java)
                            intent.putExtra("type","local")
                            startActivity(intent)
                        }

                        override fun fail() {
                            Toast.makeText(this@ItemView,"Falha na conexão com o servidor!",
                                Toast.LENGTH_SHORT).show();
                        }

                        override fun error(err: String) {
                            Toast.makeText(this@ItemView, "Error: $err", Toast.LENGTH_SHORT).show();
                        }

                    })
            }
            else if (type == "inc"){

                ApiClient().deleteTipo(prefs.getString("token","")!!,
                    id,
                    object : ConnResponse<TipoDeleteAPI> {
                        override fun success(t: TipoDeleteAPI) {
                            val intent = Intent(this@ItemView, ItemListAdmin::class.java)
                            intent.putExtra("type","inc")
                            startActivity(intent)
                        }

                        override fun fail() {
                            Toast.makeText(this@ItemView,"Falha na conexão com o servidor!",
                                Toast.LENGTH_SHORT).show();
                        }

                        override fun error(err: String) {
                            Toast.makeText(this@ItemView, "Error: $err", Toast.LENGTH_SHORT).show();
                        }

                    })

        }






    }
    fun spinlocalrefresh(){
        // Implementacao campo LOCAL
        val prefs = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE)
        val spinLocal = findViewById<Spinner>(R.id.localSpinner)
        var locais: ArrayList<Tipo>
        val LocaisString =ArrayList<String>()

        LocaisString.add("---")
        LocaisString.add("Adicionar local")
        //logica para receber do BD
        ApiClient().getLocal(prefs.getString("token", "")!!,
            object : ConnResponse<LocalAPI> {
                override fun success(t: LocalAPI) {
                    locais = t.entities as ArrayList<Tipo>
                    sortedList = locais.sortedWith(compareBy { it.name})
                    var i=0
                    while (i<sortedList.size){
                        LocaisString.add(sortedList[i].name)
                        i++
                    }

                }
                override fun fail() {
                    Toast.makeText(
                        this@ItemView, "Falha na conexão com o servidor!",
                        Toast.LENGTH_SHORT
                    ).show();
                }

                override fun error(err: String) {
                    Toast.makeText(this@ItemView, "Error: $err", Toast.LENGTH_SHORT)
                        .show();
                }

            })

        adapterLocal = ArrayAdapter(this@ItemView,android.R.layout.simple_spinner_item,LocaisString)
        spinLocal.adapter = adapterLocal
        spinLocal.onItemSelectedListener = this@ItemView
    }
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val prefs = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE)
        if (p2 == 1){
            val novoItem = EditText(this)
            val inputDialog = AlertDialog.Builder(this)
            inputDialog.setTitle("Digite o item")
            inputDialog.setView(novoItem)
            inputDialog.setPositiveButton("OK"
            ) { _, _ ->
                if (p0 != null) {
                        //logica para enviar para o BD
                    ApiClient().postLocal(prefs.getString("token", "")!!,
                        Local(0, novoItem.text.toString(), "--A ser adicionado--"),
                        object : ConnResponse<LocalAPI> {
                            override fun success(t: LocalAPI) {
                                Toast.makeText(
                                    this@ItemView, "Adicionado com sucesso!",
                                    Toast.LENGTH_SHORT
                                ).show();
                                spinlocalrefresh()
                            }

                            override fun fail() {
                                Toast.makeText(
                                    this@ItemView, "Falha na conexão com o servidor!",
                                    Toast.LENGTH_SHORT
                                ).show();
                            }

                            override fun error(err: String) {
                                Toast.makeText(this@ItemView, "Error: $err", Toast.LENGTH_SHORT).show();
                            }

                        })

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