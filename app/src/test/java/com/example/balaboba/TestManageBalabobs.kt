package com.example.balaboba

import com.example.balaboba.data.local.room.HistoryFragmentTuple
import com.example.balaboba.data.model.Balabob
import com.example.balaboba.data.repositories.ManageBalabobs

internal class TestManageBalabobs : ManageBalabobs {
    private val history = mutableListOf<HistoryFragmentTuple>()
    override suspend fun saveBalabob(balabob: Balabob) {
        history.add(
            HistoryFragmentTuple(
                balabob.query,
                balabob.response,
                balabob.style
            )
        )
    }

    override suspend fun getAllBalabob(): MutableList<HistoryFragmentTuple> {
        return history
    }
}