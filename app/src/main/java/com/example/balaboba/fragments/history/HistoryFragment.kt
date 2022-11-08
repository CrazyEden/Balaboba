package com.example.balaboba.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.balaboba.R
import com.example.balaboba.adapters.HistoryAdapter
import com.example.balaboba.data.model.Balabob
import com.example.balaboba.databinding.FragmentHistoryBinding

class HistoryFragment: Fragment(R.layout.fragment_history) {
    lateinit var binding: FragmentHistoryBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        binding.rcView.layoutManager = LinearLayoutManager(context)
        val adapter = HistoryAdapter()
        adapter.setData(listOf(Balabob(
            "xdd",
            "otvet",
            true,
            123
        )))
        binding.rcView.adapter = adapter



        return binding.root
    }
}