package com.example.balaboba.ui.fragments.history

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.balaboba.core.Communication
import com.example.balaboba.data.local.room.BalabobEntity

class HistoryCommunication : Communication<List<@JvmSuppressWildcards BalabobEntity>> {

    private val liveData = MutableLiveData<List<BalabobEntity>>()

    override fun map(value: List<BalabobEntity>) {
        liveData.postValue(value)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<BalabobEntity>>) {
        liveData.observe(owner, observer)
    }
}