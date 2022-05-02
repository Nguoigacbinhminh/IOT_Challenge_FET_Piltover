package com.example.testdatabase.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testdatabase.R
import com.example.testdatabase.model.DataClass

class DataAdapter(
    private val dataList: ArrayList<DataClass>,
    private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.heartbeat_spo2_temprature, parent, false)
        return DataViewHolder(v)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.itemImageView.setImageResource(dataList[position].images)
        holder.itemTitle.text = dataList[position].titles
        holder.itemValue.text = dataList[position].values
        holder.itemDetail.text = dataList[position].details
        holder.itemUnit.text = dataList[position].unit
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class DataViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        val itemImageView: ImageView = v.findViewById(R.id.item_image)
        val itemTitle: TextView = itemView.findViewById(R.id.item_title)
        var itemValue: TextView = itemView.findViewById(R.id.item_value)
        var itemDetail: TextView = itemView.findViewById(R.id.item_detail)
        val itemUnit: TextView = itemView.findViewById(R.id.item_unit)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}

















