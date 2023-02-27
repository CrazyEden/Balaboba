package com.example.balaboba.fragments.history

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balaboba.core.Communication
import com.example.balaboba.core.DispatchersList
import com.example.balaboba.data.local.room.HistoryFragmentTuple
import com.example.balaboba.data.repositories.ManageBalabobs
import kotlinx.coroutines.launch

@Suppress("CanBeParameter")
class HistoryViewModel(
    private val manage: ManageBalabobs,
    private val communication: Communication<List<HistoryFragmentTuple>>,
    private val dispatchersList: DispatchersList
):ViewModel(),HistoryViewModelCore {
    init {
        viewModelScope.launch(dispatchersList.getIO()) {
            communication.map(manage.getAllBalabob())
        }
    }
    override fun observe(owner: LifecycleOwner, observer: Observer<List<HistoryFragmentTuple>>) {
        communication.observe(owner, observer)
    }
}