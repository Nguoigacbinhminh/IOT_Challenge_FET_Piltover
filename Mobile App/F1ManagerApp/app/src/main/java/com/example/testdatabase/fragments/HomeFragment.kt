package com.example.testdatabase.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testdatabase.MainActivity
import com.example.testdatabase.R
import com.example.testdatabase.common.Common
import com.example.testdatabase.model.APIRespone
import com.example.testdatabase.model.DataClass
import com.example.testdatabase.remote.IAPI
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home), DataAdapter.OnItemClickListener {
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var dataAdapter: DataAdapter

    private lateinit var mService: IAPI
    private var phone: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mService = Common.api
        phone = (activity as MainActivity).phone
        getResultData(phone)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataList = ArrayList()
        dataAdapter = DataAdapter(dataList, this)
        recyclerData.layoutManager = LinearLayoutManager(view.context)
        recyclerData.adapter = dataAdapter
        refreshButton.setOnClickListener {
            getResultData(phone)
            Toast.makeText(this@HomeFragment.context, "Refresh", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getResultData(phone: String) {
        mService.getLastResult(phone).enqueue(object: Callback<APIRespone> {
            override fun onResponse(call: Call<APIRespone>, response: Response<APIRespone>) {
                if (response.body()!!.error) {
                    Toast.makeText(this@HomeFragment.context, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                } else {
                    response.body()!!.result?.let {
                        dataList.clear()
                        dataList.add(DataClass(R.drawable.heart, "Nhịp tim", it.heart, it.time, "bpm"))
                        dataList.add(DataClass(R.drawable.oxygen, "SpO2", it.spo2, it.time, "%"))
                        dataList.add(DataClass(R.drawable.thermometer, "Nhiệt độ", it.temp, it.time, "°C"))
                        dataAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<APIRespone>, t: Throwable) {
                Toast.makeText(this@HomeFragment.context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClick(position: Int) {
        val detailFragment = DetailFragment()
        (activity as MainActivity).setCurrentFragment(detailFragment, position)
    }
}





















