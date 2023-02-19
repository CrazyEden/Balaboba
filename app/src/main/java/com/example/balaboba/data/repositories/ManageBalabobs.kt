package com.example.balaboba.data.repositories

import com.example.balaboba.data.local.room.BalabobDatabase
import com.example.balaboba.data.local.room.BalabobEntity
import com.example.balaboba.data.local.room.HistoryFragmentTuple
import com.example.balaboba.data.model.Balabob

interface ManageBalabobs {
    suspend fun saveBalabob(balabob: Balabob)
    suspend fun getAllBalabob(): MutableList<HistoryFragmentTuple>

    class Base (private val dp: BalabobDatabase): ManageBalabobs {
        override suspend fun saveBalabob(balabob: Balabob) {
            dp.insertBalabob(
                BalabobEntity(
                    query = balabob.query,
                    response = balabob.response,
                    filter = balabob.filter,
                    style = balabob.style
                )
            )
        }

        override suspend fun getAllBalabob(): MutableList<HistoryFragmentTuple> {
            return dp.getAll()
        }
    }
}