package com.example.balaboba.fragments.main

import android.content.Context
import com.example.balaboba.R

interface StyleMapper {
    fun toStyleString(style:Int):String

    class Base(private val context: Context):StyleMapper{
        override fun toStyleString(style: Int): String {
            return when (style) {
                0 -> context.getString(R.string.fifth_style)
                6 -> context.getString(R.string.second_style)
                8 -> context.getString(R.string.third_style)
                9 -> context.getString(R.string.fourth_style)
                11 -> context.getString(R.string.fifth_style)
                24 -> context.getString(R.string.sixth_style)
                25 -> context.getString(R.string.seventh_style)
                else -> throw IllegalArgumentException("unknown id")
            }
        }
    }
}