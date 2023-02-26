package com.example.balaboba.fragments.main

import android.view.View
import com.example.balaboba.databinding.FragmentMainBinding

fun FragmentMainBinding.lockView(){
    filter.isClickable = false
    spinner.isClickable = false

    progressBar.visibility = View.VISIBLE
    mainTextView.visibility = View.INVISIBLE
    button.isClickable = false
}
fun FragmentMainBinding.unlockView(text:String){
    filter.isClickable = true
    spinner.isClickable = true

    progressBar.visibility = View.INVISIBLE
    mainTextView.visibility = View.VISIBLE
    mainTextView.text = text
    button.isClickable = true
}