package com.example.balaboba.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.balaboba.R
import com.example.balaboba.adapters.HistoryAdapter
import com.example.balaboba.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment: Fragment(R.layout.fragment_history) {
    private lateinit var binding: FragmentHistoryBinding
    private val vModel : HistoryViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        binding.rcView.layoutManager = LinearLayoutManager(context)
        val adapter = HistoryAdapter()
        vModel.load()
        vModel.listOfBalabobs.observe(viewLifecycleOwner){
            if (it.isNullOrEmpty())
                Toast.makeText(context,getString(R.string.error_db_is_empty),Toast.LENGTH_LONG).show()
                else adapter.setData(it)
        }

        binding.rcView.adapter = adapter



        return binding.root
    }
}