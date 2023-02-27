package com.example.balaboba

import android.app.Application
import android.content.Context
import com.example.balaboba.di.AppComponent
import com.example.balaboba.di.AppDependency
import com.example.balaboba.di.DaggerAppComponent


class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appDeps(AppDepImpl())
            .build()
    }

    private inner class AppDepImpl() : AppDependency {
        override val context: Context = this@App
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }
