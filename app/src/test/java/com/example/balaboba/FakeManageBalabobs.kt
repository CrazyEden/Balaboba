package com.example.balaboba

import com.example.balaboba.data.local.room.BalabobEntity
import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.data.model.BalabobaResponseUiState
import com.example.balaboba.data.repositories.ManageBalabobs

internal class FakeManageBalabobs(
    private val history:MutableList<BalabobEntity> = mutableListOf()
) : ManageBalabobs {
    override suspend fun saveBalabob(
        response: BalabobaResponseUiState.Success,
        request: BalabobaRequest,
        style: String
    ) {
        history.add(
            BalabobEntity(
                id = 0,
                query = request.query,
                response = response.getTextToShow(),
                filter = request.filter,
                style = style
            )
        )
    }

    override suspend fun getAllBalabob(): MutableList<BalabobEntity> {
        return history
    }

    override suspend fun removeBalabob(id: Long) {
        history.removeAll {
            it.id == id
        }
    }
}