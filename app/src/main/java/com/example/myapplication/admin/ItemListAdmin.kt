package com.example.myapplication.admin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.Base
import com.example.myapplication.base.UserView
import com.example.myapplication.conn.ApiClient
import com.example.myapplication.conn.ConnResponse
import com.example.myapplication.models.*

class ItemListAdmin : Base() {
    lateinit var type: String
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list_admin)
        type = intent.getSerializableExtra("type").toString()
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview_item)
        val prefs = getSharedPreferences("unique_name", AppCompatActivity.MODE_PRIVATE)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        var data = ArrayList<Tipo>()
        val sortedList = data
        var adapter = ItemAdapter(sortedList)
        when (type) {
            "inc"
                // catar dados de INC
            -> ApiClient().getTipo(prefs.getString("token","")!!,
                object : ConnResponse<TipoAPI> {
                    override fun success(t: TipoAPI) {
                        data = t.entities as ArrayList<Tipo>
                        val sortedList = data.sortedWith(compareBy { it.id })
                        adapter = ItemAdapter(sortedList)
                        recyclerview.adapter = adapter

                        adapter.setOnItemClickListener(object : ItemAdapter.onItemClickListener {
                            override fun onItemClick(position: Int) {
                                val item = sortedList[position]
                                var intent = Intent()
                                if (type == "user")
                                    intent = Intent(this@ItemListAdmin, UserView::class.java)
                                else
                                    intent = Intent(this@ItemListAdmin, ItemView::class.java)
                                intent.putExtra("id",item.id)
                                intent.putExtra("nome",item.name)
                                intent.putExtra("desc",item.description)
                                intent.putExtra("type",type)
                                startActivity(intent)
                            }
                        })
                    }

                    override fun fail() {
                        Toast.makeText(this@ItemListAdmin,"Falha na conexão com o servidor!",
                            Toast.LENGTH_SHORT).show();
                    }

                    override fun error(err: String) {
                        Toast.makeText(this@ItemListAdmin, "Error: $err", Toast.LENGTH_SHORT).show();
                    }

                })
            "equip" -> {
                // catar dados de EQUIP
                ApiClient().getEquipList(prefs.getString("token", "")!!,
                    object : ConnResponse<ItemList> {
                        override fun success(t: ItemList) {
                            data = t.entities as ArrayList<Tipo>
                            val sortedList = data.sortedWith(compareBy { it.id })
                            adapter = ItemAdapter(sortedList)
                            recyclerview.adapter = adapter

                            adapter.setOnItemClickListener(object :
                                ItemAdapter.onItemClickListener {
                                override fun onItemClick(position: Int) {
                                    val item = sortedList[position]
                                    var intent = Intent()
                                    if (type == "user")
                                        intent = Intent(this@ItemListAdmin, UserView::class.java)
                                    else
                                        intent = Intent(this@ItemListAdmin, ItemView::class.java)
                                    intent.putExtra("id", item.id)
                                    intent.putExtra("nome", item.name)
                                    intent.putExtra("desc", item.description)
                                    intent.putExtra("type", type)
                                    startActivity(intent)
                                }
                            })
                        }

                        override fun fail() {
                            Toast.makeText(
                                this@ItemListAdmin, "Falha na conexão com o servidor!",
                                Toast.LENGTH_SHORT
                            ).show();
                        }

                        override fun error(err: String) {
                            Toast.makeText(this@ItemListAdmin, "Error: $err", Toast.LENGTH_SHORT)
                                .show();
                        }

                    })
            }
            "local" -> {
                // catar dados de LOCAL
                ApiClient().getLocal(prefs.getString("token", "")!!,
                    object : ConnResponse<LocalAPI> {
                        override fun success(t: LocalAPI) {
                            val locais = t.entities as ArrayList<Tipo>
                            val sortedList = locais.sortedWith(compareBy { it.id })

                            adapter = ItemAdapter(sortedList)
                            recyclerview.adapter = adapter

                            adapter.setOnItemClickListener(object :
                                ItemAdapter.onItemClickListener {
                                override fun onItemClick(position: Int) {
                                    val item = sortedList[position]
                                    var intent = Intent()
                                    if (type == "user")
                                        intent = Intent(this@ItemListAdmin, UserView::class.java)
                                    else
                                        intent = Intent(this@ItemListAdmin, ItemView::class.java)
                                    intent.putExtra("id", item.id)
                                    intent.putExtra("nome", item.name)
                                    intent.putExtra("desc", item.description)
                                    intent.putExtra("type", type)
                                    startActivity(intent)
                                }
                            })
                        }

                        override fun fail() {
                            Toast.makeText(
                                this@ItemListAdmin, "Falha na conexão com o servidor!",
                                Toast.LENGTH_SHORT
                            ).show();
                        }

                        override fun error(err: String) {
                            Toast.makeText(this@ItemListAdmin, "Error: $err", Toast.LENGTH_SHORT)
                                .show();
                        }

                    })
            }
            "user"
                // catar dados de USER
            ->{

                ApiClient().getUserList(prefs.getString("token", "")!!,
                    object : ConnResponse<TipoAPI> {
                        override fun success(t: TipoAPI) {
                            val users = t.entities as ArrayList<Tipo>
                            val sortedList = users.sortedWith(compareBy { it.name })

                            adapter = ItemAdapter(sortedList)
                            recyclerview.adapter = adapter

                            adapter.setOnItemClickListener(object :
                                ItemAdapter.onItemClickListener {
                                override fun onItemClick(position: Int) {
                                    val item = sortedList[position]
                                    var intent = Intent()
                                    if (type == "user")
                                        intent = Intent(this@ItemListAdmin, UserView::class.java)
                                    else
                                        intent = Intent(this@ItemListAdmin, ItemView::class.java)
                                    intent.putExtra("id", item.id)
                                    intent.putExtra("type", type)
                                    startActivity(intent)
                                }
                            })
                        }

                        override fun fail() {
                            Toast.makeText(
                                this@ItemListAdmin, "Falha na conexão com o servidor!",
                                Toast.LENGTH_SHORT
                            ).show();
                        }

                        override fun error(err: String) {
                            Toast.makeText(this@ItemListAdmin, "Error: $err", Toast.LENGTH_SHORT)
                                .show();
                        }

                    })
            }
        }











    }
    fun newItem(view: View) {
        var intent = Intent()
        if (type == "user")
            intent = Intent(this, UserView::class.java)
        else
            intent = Intent(this, ItemView::class.java)
        intent.putExtra("type",type)
        intent.putExtra("new",true)
        startActivity(intent)
    }
}