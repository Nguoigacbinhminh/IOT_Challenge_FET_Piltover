package com.example.testdatabase.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testdatabase.MainActivity
import com.example.testdatabase.R
import com.example.testdatabase.common.Common
import com.example.testdatabase.model.APIRespone
import com.example.testdatabase.model.DataClass
import com.example.testdatabase.model.DataDetail
import com.example.testdatabase.model.ResultMeasure
import com.example.testdatabase.remote.IAPI
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {
    private lateinit var mService: IAPI
    private var phone: String = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val bundle = arguments
        val position = bundle!!.getInt("position")

        mService = Common.api
        phone = (activity as MainActivity).phone
        getListDetail(phone, position)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

////        val aaa = dummyList(20)
//        recyclerDetail.adapter = DetailAdapter(list)
//        recyclerDetail.layoutManager = LinearLayoutManager(view.context)
//        recyclerDetail.setHasFixedSize(true)
    }

    private fun getListDetail(phone: String, position: Int) {
        mService.getDetail(phone).enqueue(object: Callback<List<ResultMeasure>> {
            override fun onResponse(
                call: Call<List<ResultMeasure>>,
                response: Response<List<ResultMeasure>>
            ) {
                val detailList: List<ResultMeasure> = response.body() as List<ResultMeasure>
                val list = ArrayList<DataDetail>()
                when(position) {
                    0 -> {
                        detailList.indices.forEach { i ->
                            val item = DataDetail(detailList[i].time.toString(), detailList[i].heart.toString())
                            list += item
                        }
                    }
                    1 -> {
                        detailList.indices.forEach { i ->
                            val item = DataDetail(detailList[i].time.toString(), detailList[i].spo2.toString())
                            list += item
                        }
                    }
                    2 -> {
                        detailList.indices.forEach { i ->
                            val item = DataDetail(detailList[i].time.toString(), detailList[i].temp.toString())
                            list += item
                        }
                    }
                }
                recyclerDetail.adapter = DetailAdapter(list)
                recyclerDetail.layoutManager = LinearLayoutManager(context)
                recyclerDetail.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<List<ResultMeasure>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}