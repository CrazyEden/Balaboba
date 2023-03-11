package com.example.balaboba.fragments.main

import com.example.balaboba.FakeBalabobaNetworkRepository
import com.example.balaboba.FakeManageBalabobs
import com.example.balaboba.FakeSettingsManager
import com.example.balaboba.FakeStyleMapper
import com.example.balaboba.data.local.room.BalabobEntity
import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.dispatchers.TestDispatchersList
import com.example.balaboba.ui.fragments.main.MainViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class MainViewModelTest {
    @Test
    fun simpleSaveState() {
        val vModel = MainViewModel(
            network = FakeBalabobaNetworkRepository(),
            settingsManager = FakeSettingsManager(),
            manageBalabobs = FakeManageBalabobs(),
            communication = FakeStringCommunication.Base(),
            dispatchersList = TestDispatchersList(),
            mapper = FakeStyleMapper()
        )
        vModel.saveFilterState(false)
        vModel.saveSpinnerState(1)
        assertEquals(vModel.getFilterState(), false)
        assertEquals(vModel.getSpinnerState(), 1)
    }

    @Test
    fun twoSaveStateTwoCheck() {
        val vModel = MainViewModel(
            network = FakeBalabobaNetworkRepository(),
            settingsManager = FakeSettingsManager(),
            manageBalabobs = FakeManageBalabobs(),
            communication = FakeStringCommunication.Base(),
            dispatchersList = TestDispatchersList(),
            mapper = FakeStyleMapper()
        )
        vModel.saveFilterState(false)
        vModel.saveSpinnerState(4)

        assertEquals(vModel.getFilterState(), false)
        assertEquals(vModel.getSpinnerState(), 4)

        vModel.saveFilterState(true)
        vModel.saveSpinnerState(3)

        assertEquals(vModel.getFilterState(), true)
        assertEquals(vModel.getSpinnerState(), 3)
    }

    @Test
    fun twoSaveStateOneCheck() {
        val vModel = MainViewModel(
            network = FakeBalabobaNetworkRepository(),
            settingsManager = FakeSettingsManager(),
            manageBalabobs = FakeManageBalabobs(),
            communication = FakeStringCommunication.Base(),
            dispatchersList = TestDispatchersList(),
            mapper = FakeStyleMapper()
        )
        vModel.saveFilterState(false)
        vModel.saveSpinnerState(4)

        vModel.saveFilterState(true)
        vModel.saveSpinnerState(3)

        assertEquals(vModel.getFilterState(), true)
        assertEquals(vModel.getSpinnerState(), 3)
    }

    @Test
    fun simpleBalabob() = runBlocking<Unit> {
        val communication = FakeStringCommunication.Base()
        val vModel = MainViewModel(
            network = FakeBalabobaNetworkRepository(),
            settingsManager = FakeSettingsManager(),
            manageBalabobs = FakeManageBalabobs(),
            communication = communication,
            dispatchersList = TestDispatchersList(),
            mapper = FakeStyleMapper()
        )
        BalabobaRequest(query = "test", intro = 0, filter = true).also {
            vModel.balabobIt(it).join()
            assertEquals(communication.getCurrentState(), it.toString())
        }
    }

    @Test
    fun twoBalabobOneCheck() = runBlocking<Unit> {
        val communication = FakeStringCommunication.Base()
        val vModel = MainViewModel(
            network = FakeBalabobaNetworkRepository(),
            settingsManager = FakeSettingsManager(),
            manageBalabobs = FakeManageBalabobs(),
            communication = communication,
            dispatchersList = TestDispatchersList(),
            mapper = FakeStyleMapper()
        )
        BalabobaRequest(query = "first", intro = 2, filter = false).also {
            vModel.balabobIt(it).join()
        }
        BalabobaRequest(query = "second", intro = 1, filter = true).also {
            vModel.balabobIt(it).join()
            assertEquals(communication.getCurrentState(), it.toString())
        }
    }

    @Test
    fun testBalabobCheckBalabobCheck() = runBlocking<Unit> {
        val communication = FakeStringCommunication.Base()
        val vModel = MainViewModel(
            network = FakeBalabobaNetworkRepository(),
            settingsManager = FakeSettingsManager(),
            manageBalabobs = FakeManageBalabobs(),
            communication = communication,
            dispatchersList = TestDispatchersList(),
            mapper = FakeStyleMapper()
        )
        BalabobaRequest(query = "123", intro = 0, filter = false).also {
            vModel.balabobIt(it).join()
            assertEquals(communication.getCurrentState(), it.toString())
        }
        BalabobaRequest(query = "query", intro = 2, filter = true).also {
            vModel.balabobIt(it).join()
            assertEquals(communication.getCurrentState(), it.toString())
        }
    }

    @Test
    fun simpleBalabobCheckToSave() = runBlocking<Unit> {
        val manage = FakeManageBalabobs()
        val vModel = MainViewModel(
            network = FakeBalabobaNetworkRepository(),
            settingsManager = FakeSettingsManager(),
            manageBalabobs = manage,
            communication = FakeStringCommunication.Base(),
            dispatchersList = TestDispatchersList(),
            mapper = FakeStyleMapper()
        )
        BalabobaRequest(query = "123", intro = 0, filter = false).also {
            vModel.balabobIt(it).join()
            val expected = listOf(
                BalabobEntity(
                    query = "123", response = it.toString(), style = "style 0", id = 0, filter = false
                )
            )
            val actual = manage.getAllBalabob()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun aFewBalabobAndCheckToSave() = runBlocking<Unit> {
        val manage = FakeManageBalabobs()
        val vModel = MainViewModel(
            network = FakeBalabobaNetworkRepository(),
            settingsManager = FakeSettingsManager(),
            manageBalabobs = manage,
            communication = FakeStringCommunication.Base(),
            dispatchersList = TestDispatchersList(),
            mapper = FakeStyleMapper()
        )
        val firstRequest = BalabobaRequest(query = "123", intro = 5, filter = true)
        val secondRequest = BalabobaRequest(query = "sdh", intro = 2, filter = true)
        val thirdRequest = BalabobaRequest(query = "hbasd", intro = 0, filter = false)
        joinAll(
            vModel.balabobIt(firstRequest),
            vModel.balabobIt(secondRequest),
            vModel.balabobIt(thirdRequest)
        )

        val expected = mutableListOf(
            BalabobEntity(
                query = "123", response = firstRequest.toString(), style = "style 5", id = 0, filter = true
            ),
            BalabobEntity(
                query = "sdh", response = secondRequest.toString(), style = "style 2", id = 0, filter = true
            ),
            BalabobEntity(
                query = "hbasd", response = thirdRequest.toString(), style = "style 0", id = 0, filter = false
            )
        )
        assertEquals(expected, manage.getAllBalabob())

        val fourthRequest = BalabobaRequest(query = "jdty", intro = 1, filter = true)
        vModel.balabobIt(fourthRequest)
        expected.add(
            BalabobEntity(
                query = "jdty", response = fourthRequest.toString(), style = "style 1", id = 0, filter = true
            )
        )
        assertEquals(expected, manage.getAllBalabob())
    }
}


