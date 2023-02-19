package com.example.balaboba.fragments.main

import androidx.lifecycle.*
import com.example.balaboba.core.Communication
import com.example.balaboba.core.DispatchersList
import com.example.balaboba.data.model.Balabob
import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.data.model.BalabobaResponseUiState
import com.example.balaboba.data.repositories.BalabobaNetworkRepository
import com.example.balaboba.data.repositories.ManageBalabobs
import com.example.balaboba.data.repositories.SettingsManager
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class MainViewModel(
    private val network: BalabobaNetworkRepository,
    private val settingsManager: SettingsManager,
    private val manageBalabobs: ManageBalabobs,
    private val communication: Communication<String>,
    private val dispatchersList: DispatchersList,
    private val mapper: StyleMapper,
) : ViewModel() {
    fun observe(owner: LifecycleOwner, observer: Observer<String>) {
        communication.observe(owner, observer)
    }

    fun balabobIt(query: BalabobaRequest) =
        viewModelScope.launch(dispatchersList.getIO()) {
            val response = network.balabobIt(query)
            if (response is BalabobaResponseUiState.Success)
                manageBalabobs.saveBalabob(
                    Balabob(
                        query = query.query,
                        response = response.getTextToShow(),
                        filter = query.filter,
                        style = mapper.toStyleString(query.intro)
                    )
                )

            communication.map(response.getTextToShow())
        }

    fun getSpinnerState() = settingsManager.getSpinnerState()

    fun getFilterState() = settingsManager.getFilterState()
    fun saveSpinnerState(spinnerState: Int) {
        settingsManager.saveSpinnerState(spinnerState)
    }

    fun saveFilterState(filterState: Boolean) {
        settingsManager.saveFilterState(filterState)
    }

    class Factory @Inject constructor(
        private val network: BalabobaNetworkRepository,
        private val settingsManager: SettingsManager,
        private val manageBalabobs: ManageBalabobs,
        @Named("MainFrCommunication")
        private val communication: Communication<String>,
        private val dispatchersList: DispatchersList,
        private val styleMapper: StyleMapper,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(
                network,
                settingsManager,
                manageBalabobs,
                communication,
                dispatchersList,
                styleMapper
            ) as T
        }
    }
}
