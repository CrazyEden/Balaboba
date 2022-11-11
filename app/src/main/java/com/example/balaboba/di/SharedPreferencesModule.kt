package com.example.balaboba.di

import android.content.Context
import android.content.SharedPreferences
import com.example.balaboba.CONSTS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(CONSTS.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)




}