package com.example.balaboba.fragments.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balaboba.data.local.room.HistoryFragmentTuple
import com.example.balaboba.data.repositories.LocalStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val database: LocalStorageRepository
):ViewModel() {
    private val _listOfBalabobs =  MutableLiveData<List<HistoryFragmentTuple>?>()
    val listOfBalabobs = _listOfBalabobs

    fun load() = viewModelScope.launch(Dispatchers.IO) {
        _listOfBalabobs.postValue(database.getAllSavedForHistoryFragment())
    }
}