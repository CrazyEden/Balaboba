package com.example.balaboba.di

import com.example.balaboba.data.network.model.BalabobaRequest
import com.example.balaboba.data.network.model.BalabobaRespone
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val network:NetworkModule
) {

    suspend fun load(balabobaRequest: BalabobaRequest):Response<BalabobaRespone>
        = network.provideRetrofit().loadResponse(balabobaRequest)

}