package com.example.balaboba.data.network

import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.data.model.BalabobaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers("CONNECT_TIMEOUT:1")
    @POST("yalm/text3")
    suspend fun loadResponse(@Body req: BalabobaRequest) : Response<BalabobaResponse>
}