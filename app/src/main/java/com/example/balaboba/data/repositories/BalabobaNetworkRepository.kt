package com.example.balaboba.data.repositories

import android.content.Context
import com.example.balaboba.core.ApiInterface
import com.example.balaboba.core.BadQueryException
import com.example.balaboba.core.YandexException
import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.data.model.BalabobaResponseUiState


interface BalabobaNetworkRepository{
    suspend fun balabobIt(balabobaRequest: BalabobaRequest): BalabobaResponseUiState
    class Base(
        private val api: ApiInterface,
        private val context: Context,
    ):BalabobaNetworkRepository{
        override suspend fun balabobIt(balabobaRequest: BalabobaRequest): BalabobaResponseUiState = runCatching {
            val response = api.loadResponse(balabobaRequest)
            val body = response.body()
            if (!response.isSuccessful || body == null) throw Exception("error response")

            if (body.badQuery == 1) throw BadQueryException()
            if (body.text.isEmpty()) throw YandexException()
            BalabobaResponseUiState.Success(body)

        }.getOrElse { BalabobaResponseUiState.Error(it, context) }
    }
}