package com.example.balaboba.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.balaboba.data.model.Balabob

@Database(
    version = 2,
    entities = [BalabobEntity::class],
    exportSchema = false
)
@TypeConverters(Conv::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): BalabobDatabase
}
class Conv{
    @TypeConverter
    fun toEntity(balabob: Balabob):BalabobEntity{
        return BalabobEntity(
            query = balabob.query,
            response = balabob.response,
            filter = balabob.filter,
            style = balabob.style
        )
    }

    @TypeConverter
    fun fromEntity(balabobEntity: BalabobEntity): Balabob {
        return Balabob(
            query = balabobEntity.query,
            response = balabobEntity.response,
            filter = balabobEntity.filter,
            style = balabobEntity.style
        )
    }
}