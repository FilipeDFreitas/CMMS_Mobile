package com.example.myapplication.admin

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.Base
import com.example.myapplication.models.Item

class ItemListAdmin : Base() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list_admin)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview_item)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Item>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i: Int in 1..9) {
            if(i%2 ==0)
                data.add(0,
                    Item( "INC00239" + i , "Item"+i ,"","")
                )
            else
                data.add(0, Item( "INC00239" + i , "Item"+i ,"",""))
        }
        val sortedList = data.sortedWith(compareBy { it.id })

        // This will pass the ArrayList to our Adapter
        val adapter = ItemAdapter(sortedList)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        adapter.setOnItemClickListener(object : ItemAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@ItemListAdmin,"Foi no $position",Toast.LENGTH_SHORT).show()
                //val incident = sortedList[position]
                //val intent = Intent(this@ItemListAdmin, IncidentView::class.java)
                //intent.putExtra("user_type",intent.getSerializableExtra("user_type"))
                //intent.putExtra("inc",incident)
                //startActivity(intent)
            }
        })
    }
}