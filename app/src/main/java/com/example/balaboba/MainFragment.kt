package com.example.balaboba

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.balaboba.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val vModel:MainViewModel by viewModels()

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
        }
        binding.button.setOnClickListener {
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
                query = binding.txt.text.toString(),
                intro = intro,
                filter = binding.switch1.isActivated
            )
            println("query = " + binding.inputText.text.toString())
            println("selected intro = $intro")
            println("filter = ${binding.switch1.isActivated}")
        }







        return binding.root
    }


}
//
//export enum BalabobaStyles {
//    /**
//     * Без стиля\
//     * Напишите что-нибудь и получите продолжение от Балабобы
//     */
//    Standart = 0,
//
//    /**
//     * Короткие истории\
//     * Начните писать историю,
//     * а Балабобы продолжит — иногда страшно, но чаще смешно
//     */
//    ShortStories = 6,
//
//    /**
//     * Википедия
//     * Напишите какое-нибудь слово, а Балабоба даст этому определение
//     */
//    WikipediaSipmlified = 8,
//
//    /**
//     * Синопсисы фильмов\
//     * Напишите название фильма (существующего или нет),
//     * а Балабоба расскажет вам, о чем он
//     */
//    MovieSynopses = 9,
//
//    /**
//     * Народные мудрости\
//     * Напишите что-нибудь и получите народную мудрость
//     */
//    FolkWisdom = 11,
//    /**
//     * Инструкции по применению\
//     * Перечислите несколько предметов, а Балабоба придумает, как их использовать
//     */
//
//    UserManual = 24,
//    /**
//     * Рецепты\
//     * Перечислите съедобные ингредиенты, а Балабоба придумает рецепт с ними
//     */
//    Recipes = 25
//}