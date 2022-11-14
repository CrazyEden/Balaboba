package com.example.balaboba

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.example.balaboba.databinding.ActivityMainBinding
import com.example.balaboba.fragments.history.HistoryFragment
import com.example.balaboba.fragments.main.MainFragment
import com.example.balaboba.fragments.stylesinfo.StylesInfoFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var switcher: SwitchCompat
    private val vModel: ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        switcher =
            binding.navigator.menu.findItem(R.id.dark_theme_switcher).actionView?.findViewById(R.id.switcher)!!

        AppCompatDelegate.setDefaultNightMode(vModel.getThemeMode())
        if (vModel.getThemeMode() == 2) switcher.isChecked = true

        switcher.setOnCheckedChangeListener { _, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }


        binding.navigator.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.MainFr, MainFragment())
                        .commit()
                    binding.drawer.close()
                }
                R.id.menu_about_style -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainFr, StylesInfoFragment()).commit()
                    binding.drawer.close()
                }
                R.id.menu_about_filter -> {
                    Toast.makeText(
                        this@MainActivity, getString(R.string.about_filter), Toast.LENGTH_LONG
                    ).show()

                }
                R.id.menu_history -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainFr, HistoryFragment()).commit()
                    binding.drawer.close()
                }
                R.id.dark_theme_switcher -> {
                    switcher.toggle()
                }
                R.id.site -> {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://yandex.ru/lab/yalm"))
                    )
                }
                R.id.about_balaboba -> {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://yandex.ru/lab/yalm-howto"))
                    )
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onStop() {
        vModel.saveThemeMode(switcher.isChecked)
        super.onStop()
    }

}