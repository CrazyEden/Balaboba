package com.example.balaboba.data.network

import com.example.balaboba.data.network.model.BalabobaRequest
import com.example.balaboba.data.network.model.BalabobaRespone
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("yalm/text3")
    suspend fun loadResponse(@Body req: BalabobaRequest) : Response<BalabobaRespone>
}