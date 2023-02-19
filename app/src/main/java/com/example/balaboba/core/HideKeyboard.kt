package com.example.balaboba.core

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat

fun Activity?.hideKeyboard(){
    this?.currentFocus?.let { view ->
        ContextCompat.getSystemService(this, InputMethodManager::class.java)
            ?.hideSoftInputFromWindow(view.windowToken, 0)
    }

}