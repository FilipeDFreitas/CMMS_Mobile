package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class Hub : AppCompatActivity(){

    lateinit var data: ArrayList<Incident>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.myapplication.R.layout.activity_hub)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(com.example.myapplication.R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Incident>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..9) {
            if(i%2 ==0)
                data.add(0,Incident( "INC00239" + i , "Manutencão" ,"Aberto"))
            else
                data.add(0,Incident( "INC00239" + i , "Troca " ,"Fechado"))
        }
        var sortedList = data.sortedWith(compareBy { it.status })

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(sortedList)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        adapter.setOnItemClickListener(object : CustomAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@Hub,"Foi no $position",Toast.LENGTH_SHORT).show()
                val incident = sortedList[position]
                val intent = Intent(this@Hub, IncidentView::class.java)
                intent.putExtra("inc",incident)
                startActivity(intent)
            }
        })
    }


}