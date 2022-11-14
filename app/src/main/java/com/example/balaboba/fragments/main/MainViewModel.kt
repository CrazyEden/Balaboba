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
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val network: NetworkRepository,
    private val database: LocalStorageRepository
) :ViewModel() {
    private var _liveString = MutableLiveData<String?>()
    var liveString = _liveString

    fun load(query:String, intro:Int, filter:Boolean) = viewModelScope.launch(Dispatchers.IO){

        try {
            val res = network.load(
                BalabobaRequest(
                    query = query,
                    intro = intro,
                    filter = filter))
            if (res.isSuccessful && !res.body()?.text.isNullOrEmpty()) {
                _liveString.postValue(res.body()?.text)
                database.insertInDb(
                    BalabobEntity(
                        query = query,
                        response = res.body()?.text!!,
                        filter = filter,
                        style = intro.toStyle()))
            }else println("NUUUUUUUUUL")
            println(res.isSuccessful)
            println("CODE = ${res.code()}")
            println("ERR BODY = ${res.errorBody()}")
            println("BODY = ${res.body()}")
        }catch (e:Throwable){
            println(e)
            _liveString.postValue(null)

        }

    }

    fun getSpinnerState() = database.getSpinnerState()

    fun getFilterState() = database.getFilterState()
    fun saveSpinnerAndFilterState(filterState:Boolean,spinnerState:Int){
        database.saveFilterState(filterState)
        database.saveSpinnerState(spinnerState)
    }
}

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
