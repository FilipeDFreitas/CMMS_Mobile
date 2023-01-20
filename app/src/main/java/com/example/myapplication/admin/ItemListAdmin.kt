package com.example.myapplication.admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.Base
import com.example.myapplication.models.Item

class ItemListAdmin : Base() {
    lateinit var type: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list_admin)
        type = intent.getSerializableExtra("type").toString()
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview_item)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Item>()

        for (i: Int in 1..9) {
            if (type == "inc")
            // catar dados de INC

                data.add(0, Item("INC00239" + i, "Item " + i," "))

            else if (type == "equip")
            // catar dados de EQUIP

                data.add(0, Item("EQP0239" + i, "Item " + i," "))

            else if (type == "local")
            // catar dados de LOCAL

                data.add(0, Item("LOC00239" + i, "Item " + i," "))

            else
            // catar dados de USER

                data.add(0, Item("USR00239" + i, "Item " + i," "))


        }
        val sortedList = data.sortedWith(compareBy { it.id })

        // This will pass the ArrayList to our Adapter
        val adapter = ItemAdapter(sortedList)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        adapter.setOnItemClickListener(object : ItemAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val item = sortedList[position]
                val intent = Intent(this@ItemListAdmin, ItemView::class.java)
                intent.putExtra("id",item.id)
                intent.putExtra("nome",item.titulo)
                intent.putExtra("desc",item.desc)
                intent.putExtra("type",type)
                startActivity(intent)
            }
        })
    }
    fun newItem(view: View) {
        val intent = Intent(this, ItemView::class.java)
        intent.putExtra("type",type)
        startActivity(intent)
    }
}