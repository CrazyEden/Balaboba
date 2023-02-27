package com.example.balaboba.di

import com.example.balaboba.MainActivity
import com.example.balaboba.di.modules.BindModule
import com.example.balaboba.di.modules.CoreModule
import com.example.balaboba.di.modules.LocalStorageModule
import com.example.balaboba.di.modules.NetworkModule
import com.example.balaboba.di.modules.viewmodels.ViewModelBindModule
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
        ViewModelBindModule::class
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

