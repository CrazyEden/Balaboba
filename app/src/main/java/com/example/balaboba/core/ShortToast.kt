package com.example.balaboba.core

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context?.showShortToast(@StringRes stringId:Int){
    Toast.makeText(this, this?.getString(stringId), Toast.LENGTH_SHORT).show()
}
fun Context?.showLongToast(@StringRes stringId:Int){
    Toast.makeText(this, this?.getString(stringId), Toast.LENGTH_LONG).show()
}