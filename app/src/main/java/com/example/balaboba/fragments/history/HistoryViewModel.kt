package com.example.balaboba.fragments.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.balaboba.data.local.room.HistoryFragmentTuple
import com.example.balaboba.data.repositories.LocalStorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class HistoryViewModel(
    private val database: LocalStorageRepository
):ViewModel() {
    private val _listOfBalabobs =  MutableLiveData<List<HistoryFragmentTuple>?>()
    val listOfBalabobs = _listOfBalabobs

    fun load() = viewModelScope.launch(Dispatchers.IO) {
        _listOfBalabobs.postValue(database.getAllSavedForHistoryFragment())
    }
    class Factory @Inject constructor(private val database: LocalStorageRepository)
        : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return HistoryViewModel(database) as T
        }
    }
}