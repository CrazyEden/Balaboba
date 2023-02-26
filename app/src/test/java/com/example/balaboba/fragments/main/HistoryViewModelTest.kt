package com.example.balaboba.fragments.main

import com.example.balaboba.TestManageBalabobs
import com.example.balaboba.TestTupleCommunication
import com.example.balaboba.data.local.room.HistoryFragmentTuple
import com.example.balaboba.dispatchers.TestDispatchersList
import com.example.balaboba.fragments.history.HistoryViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test


internal class HistoryViewModelTest{
    @Test
    fun autoLoadHistoryWhenViewModelInit() = runBlocking<Unit>{
        val communication = TestTupleCommunication.Base()
        val expected = mutableListOf(
            HistoryFragmentTuple(query = "", response = "23t", style = ""),
            HistoryFragmentTuple(query = "124", response = "", style = "dah"),
            HistoryFragmentTuple(query = "sdg", response = "fdha", style = "as"),
        )
        val vModel= HistoryViewModel(
            manage = TestManageBalabobs(expected),
            communication = communication,
            dispatchersList = TestDispatchersList()
        )
        delay(1000)
        assertEquals(expected,communication.getCurrentState())
    }
}