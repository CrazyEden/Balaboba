package com.example.balaboba.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.balaboba.data.model.Balabob

@Dao
interface BalabobDatabase {

    @Insert
    suspend fun insertBalabob(balabob:BalabobEntity)

    @Query("SELECT * from balabobstable ")
    suspend fun getAllBalabobs():MutableList<Balabob>?

    @Query("SELECT `query`,response,style from balabobstable")
    suspend fun getAllMinidata():MutableList<HistoryFragmentTuple>?
}