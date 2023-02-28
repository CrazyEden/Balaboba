package com.example.balaboba

import com.example.balaboba.ui.fragments.main.StyleMapper

internal class FakeStyleMapper : StyleMapper {
    override fun toStyleString(style: Int): String {
        return "style $style"
    }
}