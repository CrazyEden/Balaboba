package com.example.balaboba

import com.example.balaboba.fragments.main.StyleMapper

internal class TestStyleMapper : StyleMapper {
    override fun toStyleString(style: Int): String {
        return "style $style"
    }
}