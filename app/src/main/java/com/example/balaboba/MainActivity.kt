package com.example.balaboba

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.balaboba.databinding.ActivityMainBinding
import com.example.balaboba.fragments.history.HistoryFragment
import com.example.balaboba.fragments.stylesinfo.StylesInfoFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.drawer.open()
        binding.navigator.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_about->{

                    supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.MainFr, StylesInfoFragment())
                        .commit()
                }
                R.id.menu_filter->{
                    Toast.makeText(this@MainActivity,"Он что-то делает, а что - неизвестно",Toast.LENGTH_LONG).show()
                }
                R.id.menu_history->{
                    supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.MainFr, HistoryFragment())
                        .commit()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

}