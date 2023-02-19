package com.example.balaboba

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.balaboba.data.repositories.NightModeManager
import javax.inject.Inject


class ActivityViewModel constructor(
    private val nightModeManager: NightModeManager
) :ViewModel() {
    fun getThemeMode() = nightModeManager.getThemeMode()
    fun saveThemeMode(isChecked:Boolean) =
        nightModeManager.saveThemeMode(if (isChecked) 2 else 1)

    class Factory @Inject constructor(
        private val nightModeManager: NightModeManager
    ):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ActivityViewModel(nightModeManager) as T
        }
    }
}