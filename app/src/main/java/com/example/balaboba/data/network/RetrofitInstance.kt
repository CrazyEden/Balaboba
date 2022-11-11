package com.example.balaboba.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://yandex.ru/lab/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
//    .baseUrl(https://yandex.com/lab/api/)      //spare urls
//    .baseUrl("https://zeapi.yandex.net/lab/api/")

    val api: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
    private val client = OkHttpClient.Builder()
        .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .build()

}