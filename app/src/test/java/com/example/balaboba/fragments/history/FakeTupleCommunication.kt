package com.example.balaboba.fragments.history

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.balaboba.core.Communication
import com.example.balaboba.data.local.room.HistoryFragmentTuple

internal interface FakeTupleCommunication : Communication<List<HistoryFragmentTuple>> {
    fun getCurrentState(): List<HistoryFragmentTuple>
    class Base() : FakeTupleCommunication {
        private var value = listOf<HistoryFragmentTuple>()
        override fun map(value: List<HistoryFragmentTuple>) {
            this.value = value
        }

        override fun observe(
            owner: LifecycleOwner,
            observer: Observer<List<HistoryFragmentTuple>>
        ) = Unit

        override fun getCurrentState(): List<HistoryFragmentTuple> {
            return value
        }
    }
}