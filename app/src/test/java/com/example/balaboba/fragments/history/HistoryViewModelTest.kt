package com.example.balaboba.fragments.history

import com.example.balaboba.FakeManageBalabobs
import com.example.balaboba.data.local.room.BalabobEntity
import com.example.balaboba.dispatchers.TestDispatchersList
import com.example.balaboba.ui.fragments.history.HistoryViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test


internal class HistoryViewModelTest {
    @Test
    fun autoLoadHistoryWhenViewModelInit() = runBlocking<Unit> {
        val communication = FakeTupleCommunication.Base()
        val expected = mutableListOf(
            BalabobEntity(id = 1, query = "фыпфыв", response = "", filter = false, style = "gvew"),
            BalabobEntity(
                id = 2,
                query = "1к23й",
                response = "еавпр",
                filter = true,
                style = "hfg"
            ),
            BalabobEntity(id = 3, query = "стми", response = "ыфвара", filter = true, style = "32"),
            BalabobEntity(
                id = 4,
                query = "укцер",
                response = "345ке",
                filter = false,
                style = "fsdhg"
            )
        )

        @Suppress("UNUSED_VARIABLE")
        val vModel = HistoryViewModel(
            manage = FakeManageBalabobs(expected),
            communication = communication,
            dispatchersList = TestDispatchersList()
        )
        delay(1000)
        assertEquals(expected, communication.getCurrentState())
    }

    @Test
    fun simpleDeleteItem() = runBlocking<Unit> {
        val communication = FakeTupleCommunication.Base()
        val initState = mutableListOf(
            BalabobEntity(id = 1, query = "фыпфыв", response = "", filter = false, style = "gvew"),
            BalabobEntity(id = 2, query = "1к23й", response = "еавпр", filter = true, style = "hfg")
        )

        @Suppress("UNUSED_VARIABLE")
        val vModel = HistoryViewModel(
            manage = FakeManageBalabobs(initState),
            communication = communication,
            dispatchersList = TestDispatchersList()
        )
        vModel.deleteBalabob(1)
        val expected = mutableListOf(
            BalabobEntity(id = 2, query = "1к23й", response = "еавпр", filter = true, style = "hfg")
        )
        vModel.getHistory().join()
        assertEquals(communication.getCurrentState(),expected)
    }

    @Test
    fun deleteTwo() = runBlocking<Unit> {
        val communication = FakeTupleCommunication.Base()
        val initList = mutableListOf(
            BalabobEntity(id = 1, query = "фыпфыв", response = "", filter = false, style = "gvew"),
            BalabobEntity(id = 2, query = "1к23й", response = "еавпр", filter = true, style = "hfg"),
            BalabobEntity(id = 3, query = "1к23й", response = "еавпр", filter = true, style = "hfg"),
            BalabobEntity(id = 4, query = "1к23й", response = "еавпр", filter = true, style = "hfg")
        )

        @Suppress("UNUSED_VARIABLE")
        val vModel = HistoryViewModel(
            manage = FakeManageBalabobs(initList),
            communication = communication,
            dispatchersList = TestDispatchersList()
        )
        vModel.deleteBalabob(1)
        vModel.deleteBalabob(4)
        val expected = mutableListOf(
            BalabobEntity(id = 2, query = "1к23й", response = "еавпр", filter = true, style = "hfg"),
            BalabobEntity(id = 3, query = "1к23й", response = "еавпр", filter = true, style = "hfg"),
        )
        vModel.getHistory().join()
        assertEquals(communication.getCurrentState(),expected)
    }
}