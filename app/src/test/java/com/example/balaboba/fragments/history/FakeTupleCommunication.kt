package com.example.balaboba.fragments.history

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.balaboba.core.Communication
import com.example.balaboba.data.local.room.BalabobEntity

internal interface FakeTupleCommunication : Communication<List<BalabobEntity>> {
    fun getCurrentState(): List<BalabobEntity>
    class Base() : FakeTupleCommunication {
        private var value = listOf<BalabobEntity>()
        override fun map(value: List<BalabobEntity>) {
            this.value = value
        }

        override fun observe(
            owner: LifecycleOwner,
            observer: Observer<List<BalabobEntity>>
        ) = Unit

        override fun getCurrentState(): List<BalabobEntity> {
            return value
        }
    }
}