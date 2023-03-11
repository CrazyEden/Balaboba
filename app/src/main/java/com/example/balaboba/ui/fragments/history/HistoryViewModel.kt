package com.example.balaboba.ui.fragments.history

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balaboba.core.Communication
import com.example.balaboba.core.DispatchersList
import com.example.balaboba.data.local.room.BalabobEntity
import com.example.balaboba.data.repositories.ManageBalabobs
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val manage: ManageBalabobs,
    private val communication: Communication<List<BalabobEntity>>,
    private val dispatchersList: DispatchersList,
) : ViewModel(), HistoryViewModelCore {
    init {
        getHistory()
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<BalabobEntity>>) {
        communication.observe(owner, observer)
    }

    override fun getHistory(): Job = viewModelScope.launch(dispatchersList.getIO()) {
        communication.map(manage.getAllBalabob())
    }

    override fun deleteBalabob(id: Long): Job = viewModelScope.launch(dispatchersList.getIO()) {
        manage.removeBalabob(id)
    }
}