package com.example.balaboba.fragments.history

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.balaboba.core.Communication
import com.example.balaboba.data.local.room.HistoryFragmentTuple

class HistoryCommunication : Communication<List<@JvmSuppressWildcards HistoryFragmentTuple>> {

    private val liveData = MutableLiveData<List<HistoryFragmentTuple>>()

    override fun map(value: List<HistoryFragmentTuple>) {
        liveData.postValue(value)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<HistoryFragmentTuple>>) {
        liveData.observe(owner, observer)
    }
}