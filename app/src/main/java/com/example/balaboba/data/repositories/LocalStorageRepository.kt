package com.example.balaboba.data.repositories

import android.content.SharedPreferences
import com.example.balaboba.CONSTS
import com.example.balaboba.data.local.room.AppDatabase
import com.example.balaboba.data.local.room.BalabobEntity
import com.example.balaboba.data.local.room.HistoryFragmentTuple
import javax.inject.Inject

class LocalStorageRepository @Inject constructor(
    private val db:AppDatabase,
    private val sharedPreferences: SharedPreferences
) {
    //suspend fun getAllSavedData():List<Balabob>? = db.getDao().getAllBalabobs()
    suspend fun getAllSavedForHistoryFragment():List<HistoryFragmentTuple>? = db.getDao().getAllMinidata()

    suspend fun insertInDb(balabobEntity: BalabobEntity){
        db.getDao().insertBalabob(balabobEntity)
    }

    fun saveSpinnerState(selectedItemId:Int)=
        sharedPreferences
            .edit()
            .putInt(CONSTS.SPINNER_KEY,selectedItemId)
            .apply()

    fun getSpinnerState():Int = sharedPreferences.getInt(CONSTS.SPINNER_KEY,0)

    fun saveFilterState(isFilterActive:Boolean)=
        sharedPreferences
            .edit()
            .putBoolean(CONSTS.FILTER_KEY,isFilterActive)
            .apply()

    fun getFilterState() = sharedPreferences.getBoolean(CONSTS.FILTER_KEY,false)

    fun saveThemeMode(stateNightMode:Int)=
        sharedPreferences
            .edit()
            .putInt(CONSTS.THEME_KEY,stateNightMode)
            .apply()

    fun getThemeMode()=
        sharedPreferences.getInt(CONSTS.THEME_KEY,1)

}