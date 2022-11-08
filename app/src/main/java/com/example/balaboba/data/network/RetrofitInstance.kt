package com.example.balaboba.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://yandex.ru/lab/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
//    .baseUrl(https://yandex.ru/lab/api/)
//    .baseUrl("https://zeapi.yandex.net/lab/api/")

    val api: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}