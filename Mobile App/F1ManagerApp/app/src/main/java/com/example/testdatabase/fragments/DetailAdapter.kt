package com.example.testdatabase.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testdatabase.R
import com.example.testdatabase.model.DataDetail
import com.example.testdatabase.model.ResultMeasure
import kotlinx.android.synthetic.main.layout_detail.view.*

class DetailAdapter(private val detailList: List<DataDetail>) : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.layout_detail, parent, false)
        return DetailViewHolder(v)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.time.text = detailList[position].time
        holder.result.text = detailList[position].result
    }

    override fun getItemCount(): Int {
        return detailList.size
    }

    class DetailViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val time: TextView = v.time_measure
        val result: TextView = v.result_measure
    }

}