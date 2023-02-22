package com.example.balaboba.fragments.history

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.balaboba.core.Communication
import com.example.balaboba.core.DispatchersList
import com.example.balaboba.data.local.room.HistoryFragmentTuple
import com.example.balaboba.data.repositories.ManageBalabobs
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


class HistoryViewModel(
    private val manage: ManageBalabobs,
    private val communication: Communication<List<HistoryFragmentTuple>>,
    private val dispatchersList: DispatchersList
):ViewModel() {
    init {
        viewModelScope.launch(dispatchersList.getIO()) {
            communication.map(manage.getAllBalabob())
        }
    }
    fun observe(owner: LifecycleOwner, observer: Observer<List<HistoryFragmentTuple>>) {
        communication.observe(owner, observer)
    }
    class Factory @Inject constructor(
        private val manage: ManageBalabobs,
        @Named("HistoryCommunication")
        private val communication: Communication<List<HistoryFragmentTuple>>,
        private val dispatchersList: DispatchersList
    )
        : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return HistoryViewModel(manage,communication,dispatchersList) as T
        }
    }
}