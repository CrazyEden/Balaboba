package com.example.balaboba

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.balaboba.databinding.FragmentMainBinding


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
            println("SELECTED POSITION = ${binding.spinner.selectedItemPosition}")
            println("FILTER = ") //TODO filter
            when(binding.spinner.selectedItemPosition){
                0->{
                    vModel.load(
                        query = binding.txt.text.toString(),
                        intro = 0,
                        filter = binding.switch1.isActivated
                    )
                }
                1->{
                    vModel.load(
                        query = binding.txt.text.toString(),
                        intro = 6,
                        filter = binding.switch1.isActivated
                    )
                }
                2->{
                    vModel.load(
                        query = binding.txt.text.toString(),
                        intro = 8,
                        filter = binding.switch1.isActivated
                    )
                }
                3->{
                    vModel.load(
                        query = binding.txt.text.toString(),
                        intro = 9,
                        filter = binding.switch1.isActivated
                    )
                }
                4->{
                    vModel.load(
                        query = binding.txt.text.toString(),
                        intro = 11,
                        filter = binding.switch1.isActivated
                    )
                }
                5->{
                    vModel.load(
                        query = binding.txt.text.toString(),
                        intro = 24,
                        filter = binding.switch1.isActivated
                    )
                }
                6->{
                    vModel.load(
                        query = binding.txt.text.toString(),
                        intro = 25,
                        filter = binding.switch1.isActivated
                    )
                }
            }

            println("query = " + binding.inputText.text.toString())
            println("selected spinner = " + binding.spinner.selectedItemPosition) //0

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