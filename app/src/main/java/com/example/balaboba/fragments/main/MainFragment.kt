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
class MainFragment : Fragment(R.layout.fragment_main) {
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


        vModel.liveString.observe(viewLifecycleOwner){
            if(it.isNullOrEmpty()) return@observe
            println(it)
            binding.txt.text = it
            binding.progressBar.visibility = View.INVISIBLE
            binding.txt.visibility = View.VISIBLE
            binding.button.isClickable = true
        }
        binding.button.setOnClickListener {
            if(binding.inputText.text.isNullOrEmpty()) {
                Toast.makeText(context,getString(R.string.input_text_is_empty), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            binding.progressBar.visibility = View.VISIBLE
            binding.txt.visibility = View.INVISIBLE
            binding.button.isClickable = false
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
                filter = binding.switch1.isChecked
            )
        }
        return binding.root
    }


}
