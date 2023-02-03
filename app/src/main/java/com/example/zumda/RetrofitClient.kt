package com.example.zumda

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val aPI: API
        get() = retrofit.create(API::class.java)

    companion object {
        private const val BASE_URL = "https://zumda-client.devdata.uz/api/"
        private var mInstance: RetrofitClient? = null

        @get:Synchronized
        val instance: RetrofitClient?
            get() {
                if (mInstance == null) {
                    mInstance = RetrofitClient()
                }
                return mInstance
            }
    }
}