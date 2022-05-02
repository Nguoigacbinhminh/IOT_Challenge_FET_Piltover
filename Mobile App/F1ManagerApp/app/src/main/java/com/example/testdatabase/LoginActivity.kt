package com.example.testdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.testdatabase.common.Common
import com.example.testdatabase.model.APIRespone
import com.example.testdatabase.remote.IAPI
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var mService: IAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mService = Common.api

        login_button.setOnClickListener {
            authenticateUser(phone_number_editText.text.toString(), password_editText.text.toString())
        }

        register_now.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun authenticateUser(phone: String, password: String) {
        mService.loginUser(phone, password).enqueue(object: Callback<APIRespone>{
            override fun onResponse(call: Call<APIRespone>, response: Response<APIRespone>) {
                if (response.body()!!.error) {
                    Toast.makeText(this@LoginActivity, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("phone_key", phone)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<APIRespone>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}


















