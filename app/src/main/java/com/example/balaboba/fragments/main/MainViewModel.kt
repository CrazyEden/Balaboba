package com.example.balaboba.fragments.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balaboba.data.local.room.BalabobEntity
import com.example.balaboba.data.network.model.BalabobaRequest
import com.example.balaboba.di.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val rep:Repository
) :ViewModel() {
    private var _liveString = MutableLiveData<String?>()
    var liveString = _liveString
    fun load(query:String, intro:Int, filter:Boolean) = viewModelScope.launch(Dispatchers.IO){

         val res = rep.load(BalabobaRequest(
                query = query,
                intro = intro,
                filter = filter)
        )
        _liveString.postValue(res.body()?.text)

        rep.insertInDb(
            BalabobEntity(
                id=0,
                query=query,
                response =res.body()?.text.toString() ,
                filter=filter,
                style = intro
            ))

    }
}