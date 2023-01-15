package com.example.myapplication.base

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Incident

class IncidentAdapter(private val mList: List<Incident>) : RecyclerView.Adapter<IncidentAdapter.ViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener : onItemClickListener){
        mListener = listener
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.incident_design, parent, false)

        return ViewHolder(view,mListener)
    }


    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val incident = mList[position]


        // sets the text to the textview from our itemHolder class
        holder.id.text = incident.id
        holder.info.text = incident.titulo
        holder.status.text = incident.status
        if(incident.status=="Aberto")
            holder.status.setTextColor(Color.rgb(46, 166, 23))
        else if (incident.status=="Fechado")
            holder.status.setTextColor(Color.DKGRAY)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int = mList.size


    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {

        init {
            itemView.setOnClickListener{
                listener.onItemClick(absoluteAdapterPosition)
            }
        }

        val id: TextView = itemView.findViewById(R.id.viewId)
        val info: TextView = itemView.findViewById(R.id.info)
        val status: TextView = itemView.findViewById(R.id.descricao)

    }



}
