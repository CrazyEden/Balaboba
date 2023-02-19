package com.example.balaboba.data.repositories

import android.content.SharedPreferences
import com.example.balaboba.data.local.room.BalabobDatabase
import com.example.balaboba.data.local.room.BalabobEntity
import com.example.balaboba.data.local.room.HistoryFragmentTuple
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorageRepository @Inject constructor(
    private val db: BalabobDatabase,
    private val sharedPreferences: SharedPreferences
) {

    suspend fun getAllSavedForHistoryFragment():List<HistoryFragmentTuple>? = db.getAll()

    suspend fun insertInDb(balabobEntity: BalabobEntity){
        db.insertBalabob(balabobEntity)
    }

    fun saveSpinnerState(selectedItemId:Int)=
        sharedPreferences.edit().putInt(SPINNER_KEY,selectedItemId).apply()

    fun getSpinnerState():Int = sharedPreferences.getInt(SPINNER_KEY,0)

    fun saveFilterState(isFilterActive:Boolean)=
        sharedPreferences.edit().putBoolean(FILTER_KEY,isFilterActive).apply()

    fun getFilterState():Boolean = sharedPreferences.getBoolean(FILTER_KEY,false)

    fun saveThemeMode(stateNightMode:Int)=
        sharedPreferences.edit().putInt(THEME_KEY,stateNightMode).apply()

    fun getThemeMode():Int=
        sharedPreferences.getInt(THEME_KEY,1)

    private val SPINNER_KEY = "SPINNER"
    private val FILTER_KEY = "FILTER"
    private val THEME_KEY = "THEME"
}

