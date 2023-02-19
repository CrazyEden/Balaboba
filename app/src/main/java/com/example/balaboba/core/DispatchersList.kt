package com.example.balaboba.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersList{
    fun getIO(): CoroutineDispatcher

    class Base : DispatchersList {
        override fun getIO(): CoroutineDispatcher {
            return Dispatchers.IO
        }
    }
}