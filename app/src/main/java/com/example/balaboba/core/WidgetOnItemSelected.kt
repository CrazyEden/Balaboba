package com.example.balaboba.core

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner

fun Spinner.setOnSelectListener(action: (position: Int) -> Unit) {
    this.onItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            action(position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}