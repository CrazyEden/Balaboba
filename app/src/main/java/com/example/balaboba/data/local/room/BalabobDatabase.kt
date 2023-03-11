package com.example.balaboba.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BalabobDatabase {
    @Insert
    suspend fun insertBalabob(balabob: BalabobEntity)

    @Query("SELECT * from balabobstable")
    suspend fun getAll(): MutableList<BalabobEntity>

    @Query("DELETE from balabobstable where id = :id")
    suspend fun deleteBalabob(id: Long)
}