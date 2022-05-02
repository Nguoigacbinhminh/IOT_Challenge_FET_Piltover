package com.example.testdatabase.remote

import com.example.testdatabase.model.APIRespone
import com.example.testdatabase.model.ResultMeasure
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IAPI {
    @FormUrlEncoded
    @POST("register.php")
    fun registerUser(@Field("name") name: String,
                     @Field("phone") phone: String,
                     @Field("password") password: String) : Call<APIRespone>

    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(@Field("phone") phone: String,
                  @Field("password") password: String) : Call<APIRespone>

    @FormUrlEncoded
    @POST("person_info.php")
    fun getUserInfo(@Field("phone") phone: String) : Call<APIRespone>

    @FormUrlEncoded
    @POST("update_user_info.php")
    fun updateUserInfo(@Field("name") name: String,
                       @Field("phone") phone: String,
                       @Field("age") age: String,
                       @Field("gender") gender: String,
                       @Field("address") address: String) : Call<APIRespone>

    @FormUrlEncoded
    @POST("get_result_measure.php")
    fun getLastResult(@Field("phone") phone: String) : Call<APIRespone>

    @FormUrlEncoded
    @POST("get_detail_list.php")
    fun getDetail(@Field("phone") phone: String) : Call<List<ResultMeasure>>

    @FormUrlEncoded
    @POST("change_password.php")
    fun changePass(@Field("phone") phone: String,
                   @Field("password") password: String) : Call<APIRespone>

}