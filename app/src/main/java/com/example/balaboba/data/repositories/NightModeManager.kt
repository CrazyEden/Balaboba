package com.example.balaboba.data.repositories

import android.content.SharedPreferences

interface NightModeManager {
    fun saveThemeMode(stateNightMode: Int)
    fun getThemeMode(): Int

    class Base(private val sharedPreferences: SharedPreferences): NightModeManager {
        override fun saveThemeMode(stateNightMode: Int) {
            sharedPreferences.edit().putInt(THEME_STATE,stateNightMode).apply()
        }

        override fun getThemeMode(): Int {
            return sharedPreferences.getInt(THEME_STATE,2)
        }
    }
    companion object{
        const val THEME_STATE = "theme_state"
    }
}