package com.example.balaboba.di


import android.content.Context
import androidx.room.Room
import com.example.balaboba.data.local.room.AppDatabase
import com.example.balaboba.data.local.room.BalabobDatabase
import com.example.balaboba.data.repositories.ManageBalabobs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoom(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "balabobstable").build()

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): BalabobDatabase = appDatabase.getDao()

    @Provides
    @Singleton
    fun provideManageBalabobs(dp: BalabobDatabase): ManageBalabobs.Base = ManageBalabobs.Base(dp)



}