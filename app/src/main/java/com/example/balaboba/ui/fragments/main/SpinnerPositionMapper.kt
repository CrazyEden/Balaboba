package com.example.balaboba.ui.fragments.main

interface SpinnerPositionMapper {
    fun positionToIntro(position:Int):Int

    class Base(): SpinnerPositionMapper {
        override fun positionToIntro(position: Int): Int {
            return when (position) {
                0 -> 0
                1 -> 6
                2 -> 8
                3 -> 9
                4 -> 11
                5 -> 24
                else -> 25
            }
        }
    }
}