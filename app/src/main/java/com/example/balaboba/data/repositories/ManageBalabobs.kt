package com.example.balaboba.data.repositories

import com.example.balaboba.data.local.room.BalabobDatabase
import com.example.balaboba.data.local.room.BalabobEntity
import com.example.balaboba.data.local.room.HistoryFragmentTuple
import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.data.model.BalabobaResponseUiState

interface ManageBalabobs {
    suspend fun saveBalabob(response: BalabobaResponseUiState.Success,request: BalabobaRequest,style:String)
    suspend fun getAllBalabob(): MutableList<HistoryFragmentTuple>

    class Base (private val dp: BalabobDatabase): ManageBalabobs {
        override suspend fun saveBalabob(
            response: BalabobaResponseUiState.Success,
            request: BalabobaRequest,
            style: String
        ) {
            dp.insertBalabob(
                BalabobEntity(
                    query = request.query,
                    response = response.getTextToShow(),
                    filter = request.filter,
                    style = style
                )
            )
        }

        override suspend fun getAllBalabob(): MutableList<HistoryFragmentTuple> {
            return dp.getAll()
        }
    }
}