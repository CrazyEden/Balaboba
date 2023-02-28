package com.example.balaboba.ui.fragments.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balaboba.core.Communication
import com.example.balaboba.core.DispatchersList
import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.data.model.BalabobaResponseUiState
import com.example.balaboba.data.repositories.BalabobaNetworkRepository
import com.example.balaboba.data.repositories.ManageBalabobs
import com.example.balaboba.data.repositories.SettingsManager
import kotlinx.coroutines.launch

class MainViewModel(
    private val network: BalabobaNetworkRepository,
    private val settingsManager: SettingsManager,
    private val manageBalabobs: ManageBalabobs,
    private val communication: Communication<String>,
    private val dispatchersList: DispatchersList,
    private val mapper: StyleMapper,
) : ViewModel(), MainViewModelCore {
    override fun observe(owner: LifecycleOwner, observer: Observer<String>) {
        communication.observe(owner, observer)
    }

    override fun balabobIt(query: BalabobaRequest) =
        viewModelScope.launch(dispatchersList.getIO()) {
            network.balabobIt(query).also {
                communication.map(it.getTextToShow())
                if (it is BalabobaResponseUiState.Success)
                    manageBalabobs.saveBalabob(
                        response =it,
                        request =query,
                        style = mapper.toStyleString(query.intro)
                    )
            }
        }

    override fun getSpinnerState() = settingsManager.getSpinnerState()

    override fun getFilterState() = settingsManager.getFilterState()
    override fun saveSpinnerState(spinnerState: Int) {
        settingsManager.saveSpinnerState(spinnerState)
    }

    override fun saveFilterState(filterState: Boolean) {
        settingsManager.saveFilterState(filterState)
    }
}