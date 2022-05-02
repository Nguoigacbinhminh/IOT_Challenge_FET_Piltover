package com.example.testdatabase

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.testdatabase.common.Common
import com.example.testdatabase.model.APIRespone
import com.example.testdatabase.remote.IAPI
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var mService: IAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mService = Common.api

        register_button.setOnClickListener {
            if (full_name_editText.text.trim().isEmpty() || phone_number_editText.text.trim().isEmpty()
                || password_editText.text.trim().isEmpty()) {
                Toast.makeText(this@RegisterActivity, "Missing information", Toast.LENGTH_SHORT).show()
            } else {
                createNewUser(full_name_editText.text.toString(), phone_number_editText.text.toString(),
                    password_editText.text.toString())
            }
        }

        login_now.setOnClickListener {
            finish()
        }
    }

    private fun createNewUser(name: String, phone: String, password: String) {
        mService.registerUser(name, phone, password)
            .enqueue(object : Callback<APIRespone> {
                override fun onResponse(call: Call<APIRespone>, response: Response<APIRespone>) {
                    if (response.body()!!.error) {
                        Toast.makeText(this@RegisterActivity, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                    } else {
                        val builder = AlertDialog.Builder(this@RegisterActivity)
                        builder.setTitle("Register Success!")
                        builder.setMessage("Congratulations, you have successfully registered an account. Please select continue to update information.")
                        builder.setPositiveButton("Continue") { _: DialogInterface, _: Int ->
                            val intent = Intent(this@RegisterActivity, UserInfoActivity::class.java)
                            intent.putExtra("phone_key", phone)
                            startActivity(intent)
                        }
                        builder.setNegativeButton("Cancel") {_:DialogInterface, _:Int ->
                            finish()
                        }
                        builder.show()
                    }
                }

                override fun onFailure(call: Call<APIRespone>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }
}





























