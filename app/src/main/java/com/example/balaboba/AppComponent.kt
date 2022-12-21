package com.example.balaboba

import android.content.Context
import com.example.balaboba.di.DatabaseModule
import com.example.balaboba.di.NetworkModule
import com.example.balaboba.di.SharedPreferencesModule
import com.example.balaboba.fragments.history.HistoryFragment
import com.example.balaboba.fragments.main.MainFragment
import dagger.Component
import dagger.Component.Builder
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class,NetworkModule::class,SharedPreferencesModule::class],
dependencies = [AppDependency::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: MainFragment)
    fun inject(historyFragment: HistoryFragment)

    @Component.Builder
    interface Builder {
        fun appDeps(appDep: AppDependency):Builder
        fun build():AppComponent
    }
}

interface AppDependency{
    val context:Context
}