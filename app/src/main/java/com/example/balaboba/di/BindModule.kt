package com.example.balaboba.di

import com.example.balaboba.core.Communication
import com.example.balaboba.core.DispatchersList
import com.example.balaboba.data.repositories.BalabobaNetworkRepository
import com.example.balaboba.data.repositories.ManageBalabobs
import com.example.balaboba.data.repositories.NightModeManager
import com.example.balaboba.data.repositories.SettingsManager
import com.example.balaboba.fragments.main.MainFrCommunication
import com.example.balaboba.fragments.main.StyleMapper
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
interface BindModule {
    @Binds
    fun bindNetworkRepository(rep: BalabobaNetworkRepository.Base): BalabobaNetworkRepository

    @Binds
    @Named("MainFrCommunication")
    fun bindMainViewModelCommunication(communication: MainFrCommunication): Communication<String>

    @Binds
    fun bindManageBalabobs(manageBalabobs: ManageBalabobs.Base): ManageBalabobs

    @Binds
    fun bindDispatchers(dispatchersList: DispatchersList.Base): DispatchersList

    @Binds
    fun bindMapper(mapper: StyleMapper.Base): StyleMapper

    @Binds
    fun bindNightModeManager(manager: NightModeManager.Base): NightModeManager

    @Binds
    fun bindSettingsManager(manager: SettingsManager.Base): SettingsManager
}