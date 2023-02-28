package com.example.balaboba.ui.fragments.stylesinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.balaboba.R
import com.example.balaboba.data.model.Style
import com.example.balaboba.databinding.FragmentStylesInfoBinding

class StylesInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentStylesInfoBinding.inflate(layoutInflater, container, false).apply {
            listView.adapter = StyleInfoAdapter(
                listOf(
                    Style(getString(R.string.first_style), getString(R.string.first_style_info)),
                    Style(getString(R.string.second_style), getString(R.string.second_style_info)),
                    Style(getString(R.string.third_style), getString(R.string.third_style_info)),
                    Style(getString(R.string.fourth_style), getString(R.string.fourth_style_info)),
                    Style(getString(R.string.fifth_style), getString(R.string.fifth_style_info)),
                    Style(getString(R.string.sixth_style), getString(R.string.sixth_style_info)),
                    Style(getString(R.string.seventh_style), getString(R.string.seventh_style_info))
                )
            )
        }.root
    }
}