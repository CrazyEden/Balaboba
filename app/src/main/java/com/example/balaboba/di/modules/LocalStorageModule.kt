package com.example.balaboba.di.modules


import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.balaboba.data.local.room.AppDatabase
import com.example.balaboba.data.local.room.BalabobDatabase
import com.example.balaboba.data.repositories.ManageBalabobs
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object LocalStorageModule {

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

    @Provides
    @Singleton
    @Named("settingsSharedPreferences")
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)

}