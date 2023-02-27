package com.example.balaboba.di.modules.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class DaggerViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>,
    @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return runCatching {
            val creator = creators[modelClass] ?: creators.entries.first{
                modelClass.isAssignableFrom(it.key)
            }.value
            creator.get() as T
        }.getOrElse { throw RuntimeException(it) }
    }
}