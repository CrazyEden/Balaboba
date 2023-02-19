package com.example.balaboba.fragments.main

import android.content.Context
import android.widget.Toast
import com.example.balaboba.R
import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.databinding.FragmentMainBinding

class CreateRequest(private val binding: FragmentMainBinding, private val context: Context?) {
    fun isReady():Boolean{
        return !binding.inputText.text.isNullOrEmpty().also {
            if (!it)
                Toast.makeText(
                context,
                context?.getString(R.string.input_text_is_empty),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun getRequestData(): BalabobaRequest {
        val intro = when (binding.spinner.selectedItemPosition) {
            1 -> 6
            2 -> 8
            3 -> 9
            4 -> 11
            5 -> 24
            6 -> 25
            else -> throw IllegalArgumentException("unknown position")
        }
        return BalabobaRequest(
            query = binding.inputText.text.toString(),
            intro = intro,
            filter = binding.filter.isChecked
        )
    }
}