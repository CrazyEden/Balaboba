package com.example.balaboba.di

import android.content.Context
import android.content.SharedPreferences
import com.example.balaboba.core.DispatchersList
import com.example.balaboba.data.repositories.NightModeManager
import com.example.balaboba.data.repositories.SettingsManager
import com.example.balaboba.fragments.history.HistoryCommunication
import com.example.balaboba.fragments.main.MainFrCommunication
import com.example.balaboba.fragments.main.StyleMapper
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object CoreModule {

    @Provides
    @Singleton
    fun provideDispatchers(): DispatchersList.Base = DispatchersList.Base()

    @Provides
    @Singleton
    fun provideMainFrCommunication(): MainFrCommunication = MainFrCommunication()

    @Provides
    @Singleton
    fun provideHistoryCommunication(): HistoryCommunication = HistoryCommunication()

    @Provides
    @Singleton
    fun provideSettingsManager(
        @Named("settingsSharedPreferences") sharedPreferences: SharedPreferences
    ): SettingsManager.Base = SettingsManager.Base(sharedPreferences)

    @Provides
    @Singleton
    fun provideStyleMapper(context: Context): StyleMapper.Base = StyleMapper.Base(context)

    @Provides
    @Singleton
    fun provideNightModeManager(
        @Named("settingsSharedPreferences") sharedPreferences: SharedPreferences
    ): NightModeManager.Base = NightModeManager.Base(sharedPreferences)
}