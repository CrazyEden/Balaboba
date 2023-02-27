package com.example.balaboba.di.modules.viewmodels

import com.example.balaboba.ActivityViewModel
import com.example.balaboba.core.DispatchersList
import com.example.balaboba.data.repositories.BalabobaNetworkRepository
import com.example.balaboba.data.repositories.ManageBalabobs
import com.example.balaboba.data.repositories.NightModeManager
import com.example.balaboba.data.repositories.SettingsManager
import com.example.balaboba.fragments.history.HistoryCommunication
import com.example.balaboba.fragments.history.HistoryViewModel
import com.example.balaboba.fragments.main.MainFrCommunication
import com.example.balaboba.fragments.main.MainViewModel
import com.example.balaboba.fragments.main.StyleMapper
import dagger.Module
import dagger.Provides

@Module
class ViewModelProvideModule{
    @Provides
    fun provideHistoryViewModel(
        manage: ManageBalabobs,
        communication: HistoryCommunication,
        dispatchersList: DispatchersList,
    ): HistoryViewModel {
        return HistoryViewModel(
            manage = manage,
            communication = communication,
            dispatchersList = dispatchersList
        )
    }
    @Provides
    fun provideMainViewModel(
        network: BalabobaNetworkRepository,
        settingsManager: SettingsManager,
        manageBalabobs: ManageBalabobs,
        communication: MainFrCommunication,
        dispatchersList: DispatchersList,
        mapper: StyleMapper,
    ): MainViewModel {
        return MainViewModel(
            network = network,
            settingsManager = settingsManager,
            manageBalabobs = manageBalabobs,
            communication = communication,
            dispatchersList = dispatchersList,
            mapper = mapper
        )
    }
    @Provides
    fun provideActivityViewModul(
        nightModeManager: NightModeManager
    ):ActivityViewModel{
        return ActivityViewModel(nightModeManager)
    }
}