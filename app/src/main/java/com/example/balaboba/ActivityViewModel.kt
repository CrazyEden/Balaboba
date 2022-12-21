package com.example.balaboba

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.balaboba.data.repositories.LocalStorageRepository
import javax.inject.Inject


class ActivityViewModel constructor(
    private val localStorageRepository: LocalStorageRepository
) :ViewModel() {
    fun getThemeMode() = localStorageRepository.getThemeMode()
    fun saveThemeMode(isChecked:Boolean) =
        localStorageRepository.saveThemeMode(if (isChecked) 2 else 1)
    class Factory @Inject constructor(
        private val localStorageRepository: LocalStorageRepository
    ):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ActivityViewModel(localStorageRepository) as T
        }
    }
}