package com.example.balaboba.fragments.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.balaboba.R
import com.example.balaboba.appComponent
import com.example.balaboba.core.hideKeyboard
import com.example.balaboba.core.setOnSelectListener
import com.example.balaboba.databinding.FragmentMainBinding
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var factory: MainViewModel.Factory
    private val vModel: MainViewModel by viewModels { factory }
    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.spinner.apply {
            setOnSelectListener { vModel.saveSpinnerState(it) }
            setSelection(vModel.getSpinnerState())
        }
        binding.filter.apply {
            isChecked = vModel.getFilterState()
            setOnCheckedChangeListener { _, isChecked -> vModel.saveFilterState(isChecked) }
        }

        binding.scroller.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY > 0) binding.button.hide()
            if (scrollY == 0) binding.button.show()
        }

        vModel.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.INVISIBLE
            binding.txt.visibility = View.VISIBLE
            binding.button.isClickable = true
            binding.txt.text = it
        }
        binding.inputText.setOnEditorActionListener { _, _, _ ->
            CreateRequest(binding, context).apply {
                if (isReady()) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.txt.visibility = View.INVISIBLE
                    binding.button.isClickable = false
                    vModel.balabobIt(getRequestData())
                }
            }
            activity.hideKeyboard()
            true
        }
        binding.button.setOnClickListener {
            CreateRequest(binding, context).apply {
                if (isReady()) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.txt.visibility = View.INVISIBLE
                    binding.button.isClickable = false
                    vModel.balabobIt(getRequestData())
                }
            }
            activity.hideKeyboard()
        }
        return binding.root
    }
}
