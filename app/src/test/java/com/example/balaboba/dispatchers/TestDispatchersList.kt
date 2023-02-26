package com.example.balaboba.dispatchers

import com.example.balaboba.core.DispatchersList
import kotlinx.coroutines.CoroutineDispatcher

internal class TestDispatchersList(
    private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
) : DispatchersList {

    override fun getIO(): CoroutineDispatcher = dispatcher
}