package com.example.testdatabase.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.testdatabase.MainActivity
import com.example.testdatabase.R
import com.example.testdatabase.common.Common
import com.example.testdatabase.model.APIRespone
import com.example.testdatabase.remote.IAPI
import kotlinx.android.synthetic.main.fragment_change_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordFragment : Fragment() {

    private lateinit var mService: IAPI
    private var phone: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mService = Common.api
        phone = (activity as MainActivity).phone
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        change_password_button.setOnClickListener {
                authenticateUser(phone, old_password_editText.text.toString())
        }
    }

    private fun authenticateUser(phone: String, password: String) {
        mService.loginUser(phone, password).enqueue(object: Callback<APIRespone> {
            override fun onResponse(call: Call<APIRespone>, response: Response<APIRespone>) {
                if (response.body()!!.error) {
                    Toast.makeText(this@ChangePasswordFragment.context, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                } else {
                    if (new_password_editText.text.trim().isEmpty()) {
                        Toast.makeText(this@ChangePasswordFragment.context, "Missing information", Toast.LENGTH_SHORT).show()
                    } else {
                        changePassword(phone, new_password_editText.text.toString())
                    }
                }
            }

            override fun onFailure(call: Call<APIRespone>, t: Throwable) {
                Toast.makeText(this@ChangePasswordFragment.context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun changePassword(phone: String, password: String) {
        mService.changePass(phone, password).enqueue(object: Callback<APIRespone> {
            override fun onResponse(call: Call<APIRespone>, response: Response<APIRespone>) {
                if (response.body()!!.error) {
                    Toast.makeText(this@ChangePasswordFragment.context, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ChangePasswordFragment.context, "Đổi mật khẩu thành công",
                        Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<APIRespone>, t: Throwable) {
                Toast.makeText(this@ChangePasswordFragment.context, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }
}