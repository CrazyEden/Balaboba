package com.example.balaboba

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.balaboba.core.Communication

internal interface TestStringCommunication : Communication<String> {
    fun getCurrentState(): String
    class Base() : TestStringCommunication {
        private var value: String = ""
        override fun map(value: String) {
            this.value = value
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<String>) = Unit

        override fun getCurrentState(): String {
            return value
        }
    }
}