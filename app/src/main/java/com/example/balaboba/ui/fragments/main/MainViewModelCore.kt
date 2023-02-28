package com.example.balaboba.ui.fragments.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.balaboba.data.model.BalabobaRequest
import kotlinx.coroutines.Job

interface MainViewModelCore{
    fun observe(owner: LifecycleOwner, observer: Observer<String>)
    fun balabobIt(query: BalabobaRequest): Job
    fun getSpinnerState():Int
    fun getFilterState():Boolean
    fun saveSpinnerState(spinnerState: Int)
    fun saveFilterState(filterState: Boolean)
}