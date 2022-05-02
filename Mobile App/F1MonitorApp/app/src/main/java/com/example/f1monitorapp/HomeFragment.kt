package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f1monitorapp.R
import com.example.f1monitorapp.RecyclerViewAdapter
import com.example.myapplication.data.DataClass
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), RecyclerViewAdapter.ClickListener {

    private lateinit var adapter: RecyclerViewAdapter
    private val listData: ArrayList<DataClass> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildDisplayData()
        initRecyclerView(view)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {

            }
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = RecyclerViewAdapter(listData, this)
        recyclerView.adapter = adapter
    }

    private fun buildDisplayData() {
        listData.clear()
        listData.add(DataClass(R.drawable.heart, "Nhịp tim", "120/70"))
        listData.add(DataClass(R.drawable.oxygen, "SpO2", "99%"))
        listData.add(DataClass(R.drawable.thermometer, "Nhiệt độ", "365 oC"))
    }

    override fun onItemClick(dataClass: DataClass) {
        Toast.makeText(context, "you are clicked item", Toast.LENGTH_SHORT).show()
    }
}
