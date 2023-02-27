package com.example.balaboba.fragments.history

import com.example.balaboba.FakeManageBalabobs
import com.example.balaboba.data.local.room.HistoryFragmentTuple
import com.example.balaboba.dispatchers.TestDispatchersList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test


internal class HistoryViewModelTest{
    @Test
    fun autoLoadHistoryWhenViewModelInit() = runBlocking<Unit>{
        val communication = FakeTupleCommunication.Base()
        val expected = mutableListOf(
            HistoryFragmentTuple(query = "", response = "23t", style = ""),
            HistoryFragmentTuple(query = "124", response = "", style = "dah"),
            HistoryFragmentTuple(query = "sdg", response = "fdha", style = "as"),
        )
        @Suppress("UNUSED_VARIABLE")
        val vModel= HistoryViewModel(
            manage = FakeManageBalabobs(expected),
            communication = communication,
            dispatchersList = TestDispatchersList()
        )
        delay(1000)
        assertEquals(expected,communication.getCurrentState())
    }
}