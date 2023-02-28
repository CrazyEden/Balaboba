package com.example.balaboba.di.modules

import android.content.Context
import com.example.balaboba.data.network.ApiInterface
import com.example.balaboba.data.repositories.BalabobaNetworkRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {

    @Provides
    fun provideRepository(apiInterface: ApiInterface, context: Context): BalabobaNetworkRepository.Base =
        BalabobaNetworkRepository.Base(apiInterface, context)

    @Provides
    fun provideAPi(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)

    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://yandex.com/lab/api/")
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            .build()
}