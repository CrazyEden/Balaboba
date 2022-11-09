package com.example.balaboba

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.balaboba.databinding.ActivityMainBinding
import com.example.balaboba.fragments.history.HistoryFragment
import com.example.balaboba.fragments.main.MainFragment
import com.example.balaboba.fragments.stylesinfo.StylesInfoFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var switcher: SwitchCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        switcher = binding.navigator.menu.findItem(R.id.dark_theme_switcher).actionView?.findViewById(R.id.switcher)

        switcher?.setOnCheckedChangeListener { buttonView, isChecked ->
            println("SWITCHER IS $isChecked")
            if (isChecked){
                setTheme(R.style.Theme_BalabobaDark)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.MainFr, MainFragment())
                    .commit()
            }else{
                setTheme(R.style.Theme_Balaboba)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.MainFr, MainFragment())
                    .commit()
            }
        }


        binding.navigator.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainFr, MainFragment())
                        .commit()
                }
                R.id.menu_about_style->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainFr, StylesInfoFragment())
                        .commit()
                }
                R.id.menu_about_filter->{
                    Toast.makeText(this@MainActivity,"Он что-то делает, а что - неизвестно",Toast.LENGTH_LONG).show()
                }
                R.id.menu_history->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainFr, HistoryFragment())
                        .commit()
                }

                R.id.dark_theme_switcher->{
                    switcher?.toggle()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }


}