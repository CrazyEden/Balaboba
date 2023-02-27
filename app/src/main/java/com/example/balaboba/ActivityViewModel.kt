package com.example.balaboba

import androidx.lifecycle.ViewModel
import com.example.balaboba.data.repositories.NightModeManager


class ActivityViewModel constructor(
    private val nightModeManager: NightModeManager,
) : ViewModel() {
    fun getThemeMode() = nightModeManager.getThemeMode()
    fun saveThemeMode(isChecked: Boolean) =
        nightModeManager.saveThemeMode(if (isChecked) 2 else 1)
}