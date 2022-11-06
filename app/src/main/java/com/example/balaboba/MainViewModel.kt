package com.example.balaboba

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balaboba.data.network.RetrofitInstance
import com.example.balaboba.data.network.model.BalabobaRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    private var _liveString = MutableLiveData<String?>()
    var liveString = _liveString
    fun load(query:String, intro:Int, filter:Boolean) = viewModelScope.launch(Dispatchers.IO){

        val res = RetrofitInstance.api.getList(
            BalabobaRequest(
                query = query,
                intro = 8,
                filter = filter)
        )
        println("CODE = ${res.code()}")
        println("CODE = ${res.errorBody()}")
        println("CODE = $res")
        _liveString.postValue(res.body()?.text)
    }
}