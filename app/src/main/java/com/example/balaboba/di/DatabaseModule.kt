package com.example.balaboba.di


import android.content.Context
import androidx.room.Room
import com.example.balaboba.data.local.room.AppDatabase
import com.example.balaboba.data.local.room.BalabobDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context:Context) =
        Room.databaseBuilder(context,AppDatabase::class.java,"balabobstable").build()
    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase):BalabobDatabase = appDatabase.getDao()
}