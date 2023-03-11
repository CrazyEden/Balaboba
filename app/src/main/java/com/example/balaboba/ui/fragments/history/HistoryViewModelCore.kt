package com.example.balaboba.ui.fragments.history

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.balaboba.data.local.room.BalabobEntity
import kotlinx.coroutines.Job

interface HistoryViewModelCore{
    fun observe(owner: LifecycleOwner, observer: Observer<List<BalabobEntity>>)
    fun getHistory():Job
    fun deleteBalabob(id:Long):Job
}