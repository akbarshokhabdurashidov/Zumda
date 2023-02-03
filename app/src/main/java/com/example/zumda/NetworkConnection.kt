package com.example.zumda

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkConnection {
    companion object {

        @JvmStatic
        fun getApiClient(context: Context): API {
            return getRetrofit(context).create(API::class.java)
        }

        private fun getRetrofit(context: Context): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://zumda-client.devdata.uz/api/")
                .addConverterFactory(GsonConverterFactory.create())
            if (BuildConfig.DEBUG) {
                retrofit.client(getOkhttp(context = context))
            } else {
                retrofit.client(getOkhttpRelease(context = context))
            }
//            println("getRetrofit->${retrofit.toString()}")

            return retrofit.build()
        }

        private fun getOkhttp(context: Context): OkHttpClient {

            val client = OkHttpClient().newBuilder()
                .addInterceptor(headerInterceptor())
                .addInterceptor(loggingInterceptor())
                .addInterceptor(chuckInterceptor(context))
                .build()
//            println( "getOkhttp->${client.toString()}")
            return client
        }

        private fun getOkhttpRelease(context: Context): OkHttpClient {

            val client = OkHttpClient().newBuilder()
                .addInterceptor(headerInterceptor())
                .addInterceptor(loggingInterceptor())
                .addInterceptor(chuckInterceptor(context))
                .build()
//            println( "getOkhttp->${client.toString()}")
            return client
        }

        private fun headerInterceptor(): Interceptor {

            val interceptor = Interceptor { chain ->
                val request = chain.request()
                val requestBuilder = request.newBuilder()
                return@Interceptor chain.proceed(requestBuilder.build())
            }
//            println( "headerInterceptor->${interceptor.toString()}")
            return interceptor
        }

        private fun loggingInterceptor(): Interceptor {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor { message ->
                    Log.d("MealDB:->#", message)
//                    println(message)
                }
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//            println( "loggingInterceptor->${httpLoggingInterceptor.toString()}")
            return httpLoggingInterceptor
        }

        private fun chuckInterceptor(context: Context): Interceptor {
            return ChuckerInterceptor.Builder(context)
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(true)
                .build()
        }


    }
}