package com.example.balaboba.fragments.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.balaboba.core.Communication

class MainFrCommunication : Communication<String> {
    private val livedata = MutableLiveData<String>()
    override fun map(value: String) { livedata.postValue(value) }

    override fun observe(owner: LifecycleOwner, observer: Observer<String>) {
        livedata.observe(owner, observer)
    }
}