package com.example.balaboba.di

import com.example.balaboba.data.network.ApiInterface
import com.example.balaboba.data.network.RetrofitInstance
import dagger.Module
import dagger.Provides

@Module
object NetworkModule {
    @Provides
    fun provideRetrofit():ApiInterface=RetrofitInstance.api

}