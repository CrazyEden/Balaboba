package com.example.balaboba

import androidx.lifecycle.ViewModel
import com.example.balaboba.data.repositories.LocalStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val localStorageRepository: LocalStorageRepository
) :ViewModel() {
    fun getThemeMode() = localStorageRepository.getThemeMode()
    fun saveThemeMode(isChecked:Boolean){
        if (isChecked) localStorageRepository.saveThemeMode(2)
        else localStorageRepository.saveThemeMode(1)
    }
}