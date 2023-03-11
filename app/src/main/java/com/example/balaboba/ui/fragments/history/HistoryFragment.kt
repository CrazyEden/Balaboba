package com.example.balaboba.ui.fragments.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.balaboba.R
import com.example.balaboba.appComponent
import com.example.balaboba.core.showLongToast
import com.example.balaboba.databinding.FragmentHistoryBinding
import javax.inject.Inject


class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val vModel: HistoryViewModelCore by viewModels<HistoryViewModel> { factory }
    private val adapter = HistoryAdapter()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        binding.rcView.adapter = adapter
        val swipeListener = SwipeListener(){
            vModel.deleteBalabob(adapter.getId(it))
            adapter.removeItem(it)
        }
        swipeListener.attachToRecyclerView(binding.rcView)

        vModel.observe(viewLifecycleOwner) {
            if (it.isEmpty()) context.showLongToast(R.string.error_db_is_empty)
            else adapter.setData(it)
        }
        return binding.root
    }
}