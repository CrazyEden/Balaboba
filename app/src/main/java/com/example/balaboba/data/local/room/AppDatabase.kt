package com.example.balaboba.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 2,
    entities = [BalabobEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): BalabobDatabase
}