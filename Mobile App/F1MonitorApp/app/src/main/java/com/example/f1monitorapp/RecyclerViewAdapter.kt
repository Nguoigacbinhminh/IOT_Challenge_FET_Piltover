package com.example.f1monitorapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.DataClass

class RecyclerViewAdapter(
    private val listData: List<DataClass>,
    private val clickListener: ClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.heartbeat_spo2_temprature, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemImageView.setImageResource(listData[position].images)
        holder.itemTitle.text = listData[position].titles
        holder.itemDetail.text = listData[position].details

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(listData[position])
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImageView: ImageView = view.findViewById(R.id.item_image)
        var itemTitle: TextView = itemView.findViewById(R.id.item_title)
        var itemDetail: TextView = itemView.findViewById(R.id.item_detail)
    }

    interface ClickListener {
        fun onItemClick(dataClass: DataClass)
    }
}