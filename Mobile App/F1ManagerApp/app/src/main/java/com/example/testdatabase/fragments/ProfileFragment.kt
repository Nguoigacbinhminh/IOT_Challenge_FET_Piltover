package com.example.testdatabase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import com.example.testdatabase.MainActivity
import com.example.testdatabase.R
import com.example.testdatabase.common.Common
import com.example.testdatabase.model.APIRespone
import com.example.testdatabase.remote.IAPI
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var mService: IAPI
    var radioButton: RadioButton ?= null
    lateinit var updateButton: Button
    private var phone: String = ""
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        phone = (activity as MainActivity).phone

        mService = Common.api

        getInfo(phone)
        updateButton = view.findViewById(R.id.update_button)
        updateButton.setOnClickListener {
            val intSelectButton: Int = gender_radioGroup!!.checkedRadioButtonId
            radioButton = view.findViewById(intSelectButton)

            updateInfo(name_editText.text.toString(), phone, age_editText.text.toString(),
                radioButton?.text.toString(), address_editText.text.toString())
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun getInfo(phone: String) {
        mService.getUserInfo(phone).enqueue(object: Callback<APIRespone> {
            override fun onResponse(call: Call<APIRespone>, response: Response<APIRespone>) {
                if (response.body()!!.error) {
                    Toast.makeText(this@ProfileFragment.context, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                } else {
                    response.body()!!.user?.let {
                        phone_number_editText.setText(it.phone)
                        name_editText.setText(it.name)
                        age_editText.setText(it.age.toString())
                        address_editText.setText(it.address)

                        when(it.gender) {
                            "Nam" -> radioButton = male_radioButton
                            "Nữ" -> radioButton = female_radioButton
                            "Khác" -> radioButton = other_radioButton
                        }
                        radioButton?.isChecked = true
                    }
                }
            }
            override fun onFailure(call: Call<APIRespone>, t: Throwable) {
                Toast.makeText(this@ProfileFragment.context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateInfo(name: String, phone: String, age: String, gender: String, address: String) {
        mService.updateUserInfo(name, phone, age, gender, address).enqueue(object:
            Callback<APIRespone> {
            override fun onResponse(call: Call<APIRespone>, response: Response<APIRespone>) {
                if (response.body()!!.error) {
                    Toast.makeText(this@ProfileFragment.context, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ProfileFragment.context, "Update Success", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<APIRespone>, t: Throwable) {
                Toast.makeText(this@ProfileFragment.context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}