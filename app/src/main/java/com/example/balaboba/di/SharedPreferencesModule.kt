package com.example.balaboba.di

import android.content.Context
import android.content.SharedPreferences
import com.example.balaboba.CONSTS
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(CONSTS.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)




}