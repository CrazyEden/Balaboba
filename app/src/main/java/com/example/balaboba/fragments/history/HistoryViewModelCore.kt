package com.example.balaboba.fragments.history

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.balaboba.data.local.room.HistoryFragmentTuple

interface HistoryViewModelCore{
    fun observe(owner: LifecycleOwner, observer: Observer<List<HistoryFragmentTuple>>)
}