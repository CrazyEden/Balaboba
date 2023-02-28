package com.example.balaboba.ui.activity

import androidx.lifecycle.ViewModel
import com.example.balaboba.data.repositories.NightModeManager


class ActivityViewModel(
    private val nightModeManager: NightModeManager,
) : ViewModel() {
    fun getThemeMode() = nightModeManager.getThemeMode()
    fun saveThemeMode(isChecked: Boolean) =
        nightModeManager.saveThemeMode(if (isChecked) 2 else 1)
}