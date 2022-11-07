package com.example.balaboba.di

import com.example.balaboba.data.network.ApiInterface
import com.example.balaboba.data.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit():ApiInterface=RetrofitInstance.api

}