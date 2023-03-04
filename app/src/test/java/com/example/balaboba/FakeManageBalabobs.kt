package com.example.balaboba

import com.example.balaboba.data.local.room.HistoryFragmentTuple
import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.data.model.BalabobaResponseUiState
import com.example.balaboba.data.repositories.ManageBalabobs

internal class FakeManageBalabobs(
    private val history:MutableList<HistoryFragmentTuple> = mutableListOf()
) : ManageBalabobs {
    override suspend fun saveBalabob(
        response: BalabobaResponseUiState.Success,
        request: BalabobaRequest,
        style: String
    ) {
        history.add(
            HistoryFragmentTuple(
                request.query,
                response.getTextToShow(),
                style
            )
        )
    }

    override suspend fun getAllBalabob(): MutableList<HistoryFragmentTuple> {
        return history
    }
}