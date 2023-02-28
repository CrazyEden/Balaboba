package com.example.balaboba.data.model

import android.content.Context
import com.example.balaboba.R
import com.example.balaboba.core.BadQueryException
import com.example.balaboba.core.YandexException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed interface BalabobaResponseUiState {
    fun getTextToShow(): String
    class Success(private val response: BalabobaResponse) : BalabobaResponseUiState {
        override fun getTextToShow(): String = response.text
    }

    class Error(private val thr: Throwable, private val context: Context) : BalabobaResponseUiState {
        override fun getTextToShow(): String {
            return when (thr) {
                is SocketTimeoutException -> context.getString(R.string.error_timeout)
                is UnknownHostException -> context.getString(R.string.error_no_internet)
                is YandexException -> context.getString(R.string.error_yandex_moment)
                is BadQueryException -> context.getString(R.string.error_bad_text)
                else -> thr.toString()
            }
        }
    }
}



