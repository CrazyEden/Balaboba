package com.example.balaboba.data.repositories

import android.content.SharedPreferences

interface SettingsManager {
    /*SPINNER*/
    fun saveSpinnerState(selectedItemId: Int)
    fun getSpinnerState(): Int

    /*FILTER*/
    fun saveFilterState(isActive: Boolean)
    fun getFilterState(): Boolean

    companion object {
        const val SPINNER_KEY = "spinner_state"
        const val FILTER_KEY = "filter_state"
        const val THEME_KEY = "theme_state"
    }


    class Base (
        private val sharedPreferences: SharedPreferences
    ) : SettingsManager {
        override fun saveSpinnerState(selectedItemId: Int) {
            sharedPreferences.edit().putInt(SPINNER_KEY, selectedItemId).apply()
        }

        override fun getSpinnerState(): Int {
            return sharedPreferences.getInt(SPINNER_KEY, 0)
        }

        override fun saveFilterState(isActive: Boolean) {
            sharedPreferences.edit().putBoolean(FILTER_KEY, isActive).apply()
        }

        override fun getFilterState(): Boolean {
            return sharedPreferences.getBoolean(FILTER_KEY, false)
        }
    }
}