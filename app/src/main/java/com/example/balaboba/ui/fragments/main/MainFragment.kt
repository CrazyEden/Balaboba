package com.example.balaboba.ui.fragments.main

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
import com.example.balaboba.core.hideKeyboard
import com.example.balaboba.core.setOnSelectListener
import com.example.balaboba.core.showShortToast
import com.example.balaboba.data.model.BalabobaRequest
import com.example.balaboba.databinding.FragmentMainBinding
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val spinnerMapper: SpinnerPositionMapper = SpinnerPositionMapper.Base()

    @Inject lateinit var factory: ViewModelProvider.Factory
    private val vModel: MainViewModelCore by viewModels<MainViewModel> { factory }
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
            setSelection(vModel.getSpinnerState())
            setOnSelectListener { vModel.saveSpinnerState(it) }
        }
        binding.filter.apply {
            isChecked = vModel.getFilterState()
            setOnCheckedChangeListener { _, isChecked -> vModel.saveFilterState(isChecked) }
        }

        binding.scroller.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY > 0) binding.button.hide()
            if (scrollY == 0) binding.button.show()
        }

        vModel.observe(viewLifecycleOwner) { binding.unlockView(it) }

        binding.inputText.setOnEditorActionListener { _, _, _ ->
            sendRequest()
            true
        }
        binding.button.setOnClickListener { sendRequest() }
        return binding.root
    }

    private fun sendRequest() {
        if (binding.inputText.text.isNullOrEmpty())
            return context.showShortToast(R.string.input_text_is_empty)
        val request = BalabobaRequest(
            query = binding.inputText.text.toString(),
            intro = spinnerMapper.positionToIntro(binding.spinner.selectedItemPosition),
            filter = binding.filter.isChecked
        )
        binding.lockView()
        activity.hideKeyboard()
        vModel.balabobIt(request)
    }
}
