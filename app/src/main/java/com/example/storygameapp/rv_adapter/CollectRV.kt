package com.example.storygameapp.rv_adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.storygameapp.model.CollectModel
import com.example.storygameapp.R

class CollectRV(private val itemList: MutableList<CollectModel>): RecyclerView.Adapter<CollectRV.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // holds list items
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = itemList[position]
        Log.d("position", position.toString())

        // sets text to the textView from itemHolder class?
        holder.collectTitle.text = model.collect_title
        holder.collectDesc.text = model.collect_desc
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    // support class
    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val collectTitle: TextView = itemView.findViewById(R.id.collect_name)
        val collectDesc: TextView = itemView.findViewById(R.id.collect_desc)
    }
}