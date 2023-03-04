package com.example.balaboba

import com.example.balaboba.data.repositories.SettingsManager

internal class FakeSettingsManager(
    private var spinner: Int = 1,
    private var filter: Boolean = false,
) : SettingsManager {

    override fun saveSpinnerState(selectedItemId: Int) {
        spinner = selectedItemId
    }

    override fun getSpinnerState(): Int {
        return spinner
    }

    override fun saveFilterState(isActive: Boolean) {
        filter = isActive
    }

    override fun getFilterState(): Boolean {
        return filter
    }
}