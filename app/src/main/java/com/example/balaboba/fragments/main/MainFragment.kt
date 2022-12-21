package com.example.balaboba.fragments.main

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
import com.example.balaboba.databinding.FragmentMainBinding
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var factory:MainViewModel.Factory
    private val vModel: MainViewModel by viewModels{ factory }
    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.spinner.setSelection(vModel.getSpinnerState())
        binding.filter.isChecked = vModel.getFilterState()
        binding.scroller.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY > 0)
                binding.button.hide()
            if (scrollY == 0)
                binding.button.show()
        }
        binding.inputText.setOnEditorActionListener { _, _, _ ->
            balabobIt()
            true
        }
        vModel.liveString.observe(viewLifecycleOwner) {
            setStateAsReadyToRequest()
            binding.txt.text = it
        }
        vModel.errorStr.observe(viewLifecycleOwner) {
            setStateAsReadyToRequest()
            when (it) {
                "TIMEOUT" -> showLongToast(getString(R.string.error_timeout))
                "NO_INTERNET" -> showLongToast(getString(R.string.error_no_internet))
                "YANDEX_MOMENT" -> showLongToast(getString(R.string.error_yandex_moment))
                "NO_POLITIC" -> binding.txt.text = getString(R.string.error_bad_text)
                else -> binding.txt.text = it
            }
        }
        binding.button.setOnClickListener { balabobIt() }
        return binding.root
    }



    private fun balabobIt() {
        if (binding.inputText.text.isNullOrEmpty())
            return showLongToast(getString(R.string.input_text_is_empty))
        binding.progressBar.visibility = View.VISIBLE
        binding.txt.visibility = View.INVISIBLE
        binding.button.isClickable = false
        var intro = 0
        when (binding.spinner.selectedItemPosition) {
            1 -> intro = 6
            2 -> intro = 8
            3 -> intro = 9
            4 -> intro = 11
            5 -> intro = 24
            6 -> intro = 25
        }
        vModel.load(
            query = binding.inputText.text.toString(),
            intro = intro,
            filter = binding.filter.isChecked
        )
    }

    private fun showLongToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private fun setStateAsReadyToRequest() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.txt.visibility = View.VISIBLE
        binding.button.isClickable = true
    }

}
