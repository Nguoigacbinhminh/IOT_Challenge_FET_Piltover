package com.example.testdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.testdatabase.common.Common
import com.example.testdatabase.model.APIRespone
import com.example.testdatabase.remote.IAPI
import kotlinx.android.synthetic.main.activity_user_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoActivity : AppCompatActivity() {

    private lateinit var mService: IAPI
    lateinit var gender: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        val phone: String = intent.getStringExtra("phone_key").toString()

        mService = Common.api

        getInfo(phone)
        save_button.setOnClickListener {
            updateInfo(name_editText.text.toString(), phone, age_editText.text.toString(),
                gender, address_editText.text.toString())
        }
    }

    private fun getInfo(phone: String) {
        mService.getUserInfo(phone).enqueue(object: Callback<APIRespone> {
            override fun onResponse(call: Call<APIRespone>, response: Response<APIRespone>) {
                if (response.body()!!.error) {
                    Toast.makeText(this@UserInfoActivity, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                } else {
                    response.body()!!.user?.let {
                        phone_number_editText.setText(it.phone)
                        name_editText.setText(it.name)
                    }
                }
            }
            override fun onFailure(call: Call<APIRespone>, t: Throwable) {
                Toast.makeText(this@UserInfoActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateInfo(name: String, phone: String, age: String, gender: String, address: String) {
        mService.updateUserInfo(name, phone, age, gender, address).enqueue(object: Callback<APIRespone> {
            override fun onResponse(call: Call<APIRespone>, response: Response<APIRespone>) {
                if (response.body()!!.error) {
                    Toast.makeText(this@UserInfoActivity, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this@UserInfoActivity, MainActivity::class.java)
                    intent.putExtra("phone_key", phone)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)

                }
            }
            override fun onFailure(call: Call<APIRespone>, t: Throwable) {
                Toast.makeText(this@UserInfoActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            when (view.getId()) {
                R.id.male_radioButton -> if (view.isChecked) { gender = "Nam" }
                R.id.female_radioButton -> if (view.isChecked) { gender = "Nữ" }
                R.id.other_radioButton -> if (view.isChecked) { gender = "Khác" }
            }
        }
    }
}