package com.example.balaboba

import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.data.model.BalabobaResponse
import com.example.balaboba.data.model.BalabobaResponseUiState
import com.example.balaboba.data.repositories.BalabobaNetworkRepository

internal class TestBalabobaNetworkRepository : BalabobaNetworkRepository {
    override suspend fun balabobIt(balabobaRequest: BalabobaRequest): BalabobaResponseUiState {
        return BalabobaResponseUiState.Success(
            BalabobaResponse(
                badQuery = 0,
                query = balabobaRequest.query,
                text = balabobaRequest.toString(),
                error = null,
                isCached = null,
                intro = balabobaRequest.intro,
                signature = null
            )
        )
    }
}