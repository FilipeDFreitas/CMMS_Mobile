package com.example.myapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class

        // sets the text to the textview from our itemHolder class
        holder.id.text = ItemsViewModel.id
        holder.info.text = ItemsViewModel.info
        holder.status.text = ItemsViewModel.status
        if(ItemsViewModel.status=="Aberto")
            holder.status.setTextColor(Color.rgb(46, 166, 23))
        else if (ItemsViewModel.status=="Fechado")
            holder.status.setTextColor(Color.DKGRAY)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val id: TextView = itemView.findViewById(R.id.viewId)
        val info: TextView = itemView.findViewById(R.id.info)
        val status: TextView = itemView.findViewById(R.id.status)
    }
}
