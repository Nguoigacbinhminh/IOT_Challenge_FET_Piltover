package com.example.testdatabase.common

import com.example.testdatabase.remote.IAPI
import com.example.testdatabase.remote.RetrofitClient

object Common {
    //private const val BASE_URL = "http://192.168.0.111/covid19/"
    private const val BASE_URL = "http://192.168.110.111/covid19/"

    val api: IAPI get() = RetrofitClient.getClient(BASE_URL).create(IAPI::class.java)
}