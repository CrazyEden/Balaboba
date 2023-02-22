package com.example.balaboba.fragments.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.balaboba.R
import com.example.balaboba.appComponent
import com.example.balaboba.databinding.FragmentHistoryBinding
import javax.inject.Inject


class HistoryFragment: Fragment(R.layout.fragment_history) {
    private lateinit var binding: FragmentHistoryBinding

    @Inject lateinit var factory:HistoryViewModel.Factory
    private val vModel : HistoryViewModel by viewModels {factory}


    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        val adapter = HistoryAdapter()
        binding.rcView.adapter = adapter

        vModel.observe(viewLifecycleOwner){
            if (it.isEmpty())
                Toast.makeText(context,getString(R.string.error_db_is_empty),Toast.LENGTH_LONG).show()
            else adapter.setData(it)
        }
        return binding.root
    }
}