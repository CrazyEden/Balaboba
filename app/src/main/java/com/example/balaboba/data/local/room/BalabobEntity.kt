package com.example.balaboba.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "balabobstable")
data class BalabobEntity(
    @PrimaryKey(autoGenerate = true) val id:Long = 0,
    val query:String,
    val response:String,
    val filter:Boolean,
    val style:String
)
