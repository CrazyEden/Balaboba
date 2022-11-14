package com.example.balaboba.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.balaboba.R
import com.example.balaboba.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val vModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainBinding.inflate(inflater,container,false)

        binding.spinner.setSelection(vModel.getSpinnerState())
        binding.filter.isChecked = vModel.getFilterState()
        binding.scroller.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > 0 )
                binding.button.hide();
            if (scrollY == 0)
                binding.button.show()
        }
        vModel.liveString.observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.INVISIBLE
            binding.txt.visibility = View.VISIBLE
            binding.button.isClickable = true
            if(it.isNullOrEmpty()){
                Toast.makeText(context,getString(R.string.observe_error),Toast.LENGTH_SHORT).show()
                return@observe
            }
            binding.txt.text = it
        }
        binding.button.setOnClickListener { balabobIt() }
        return binding.root
    }

    override fun onStop() {
        vModel.saveSpinnerAndFilterState(binding.filter.isChecked,binding.spinner.selectedItemPosition)
        super.onStop()
    }

    private fun balabobIt(){
        if(binding.inputText.text.isNullOrEmpty()) {
            Toast.makeText(context,getString(R.string.input_text_is_empty), Toast.LENGTH_SHORT).show()
            return
        }
        binding.progressBar.visibility = View.VISIBLE
        binding.txt.visibility = View.INVISIBLE
//        binding.button.isClickable = false
        var intro = 0 //по умолчанию без стиля
        when(binding.spinner.selectedItemPosition){
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

}
