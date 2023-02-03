package com.example.zumda

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("auth/register")
    fun createUser(
        @Body user: User?
    ): Call<ResponseBody?>?

    @POST("auth/login")
    fun checkUser(
        @Body user: User?
    ): Call<ResponseBody?>?
}