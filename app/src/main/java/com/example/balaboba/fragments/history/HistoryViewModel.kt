package com.example.balaboba.fragments.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balaboba.data.model.Balabob
import com.example.balaboba.di.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val rep: Repository
):ViewModel() {
    val b =  MutableLiveData<List<Balabob>>()

    fun load() = viewModelScope.launch(Dispatchers.IO) {
        b.postValue(rep.getAllSavedData())
    }
}