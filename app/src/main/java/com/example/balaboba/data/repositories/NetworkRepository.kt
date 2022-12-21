package com.example.balaboba.data.repositories

import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.data.model.BalabobaResponse
import com.example.balaboba.data.network.ApiInterface
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(
    private val network:ApiInterface
) {

    suspend fun load(balabobaRequest: BalabobaRequest):Response<BalabobaResponse>
        = network.loadResponse(balabobaRequest)

}