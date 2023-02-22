package com.example.balaboba

import android.content.Context
import com.example.balaboba.di.BindModule
import com.example.balaboba.di.CoreModule
import com.example.balaboba.di.LocalStorageModule
import com.example.balaboba.di.NetworkModule
import com.example.balaboba.fragments.history.HistoryFragment
import com.example.balaboba.fragments.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        LocalStorageModule::class,
        NetworkModule::class,
        CoreModule::class,
        BindModule::class,
    ],
    dependencies = [AppDependency::class]
)
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: MainFragment)
    fun inject(historyFragment: HistoryFragment)

    @Component.Builder
    interface Builder {
        fun appDeps(appDep: AppDependency): Builder
        fun build(): AppComponent
    }
}

interface AppDependency {
    val context: Context
}