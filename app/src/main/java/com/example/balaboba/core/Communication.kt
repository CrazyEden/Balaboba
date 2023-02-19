package com.example.balaboba.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface Communication<T> {
    fun map(value: T)
    fun observe(owner: LifecycleOwner, observer: Observer<T>)
}