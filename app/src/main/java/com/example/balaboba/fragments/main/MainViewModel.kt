package com.example.balaboba.fragments.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balaboba.data.local.room.BalabobEntity
import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.data.repositories.LocalStorageRepository
import com.example.balaboba.data.repositories.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val network: NetworkRepository,
    private val database: LocalStorageRepository
) :ViewModel() {
    private var _liveString = MutableLiveData<String>()
    var liveString = _liveString

    private var _errorStr = MutableLiveData<String>()
    var errorStr = _errorStr

    fun load(query:String, intro:Int, filter:Boolean) = viewModelScope.launch(Dispatchers.IO){

        runCatching {
            val res = network.load(BalabobaRequest(
                query = query,
                intro = intro,
                filter = filter))
            if (res.body()?.badQuery == 1) throw BadQueryException()
            if (res.isSuccessful && res.body()?.text.isNullOrEmpty()) throw YandexException()

            _liveString.postValue(res.body()?.text)
            database.insertInDb(BalabobEntity(
                query = query,
                response = res.body()?.text!!,
                filter = filter,
                style = intro.toStyle())
            )

        }.getOrElse {
            when(it){
                is SocketTimeoutException ->_errorStr.postValue("TIMEOUT")
                is UnknownHostException ->  _errorStr.postValue("NO_INTERNET")
                is YandexException ->       _errorStr.postValue("YANDEX_MOMENT")
                is BadQueryException ->     _errorStr.postValue("NO_POLITIC")
                else->                      _errorStr.postValue(it.toString())
            }
        }








    }

    fun getSpinnerState() = database.getSpinnerState()

    fun getFilterState() = database.getFilterState()
    fun saveSpinnerAndFilterState(filterState:Boolean,spinnerState:Int){
        database.saveFilterState(filterState)
        database.saveSpinnerState(spinnerState)
    }
}

class BadQueryException: Exception()
class YandexException: Exception()

private fun Int.toStyle(): String {
    return when(this){
        0 -> "Без стиля"
        6 -> "Короткие истории"
        8 -> "Короче, Википедия"
        9 -> "Синопсисы фильмов"
        11 -> "Народные мудрости"
        24 -> "Инструкция по применению"
        25 -> "Рецепты"
        else -> throw IllegalArgumentException("unknown id")
    }
}
