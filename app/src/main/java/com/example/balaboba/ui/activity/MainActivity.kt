package com.example.balaboba.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.ViewModelProvider
import com.example.balaboba.R
import com.example.balaboba.appComponent
import com.example.balaboba.core.showLongToast
import com.example.balaboba.databinding.ActivityMainBinding
import com.example.balaboba.ui.fragments.history.HistoryFragment
import com.example.balaboba.ui.fragments.main.MainFragment
import com.example.balaboba.ui.fragments.stylesinfo.StylesInfoFragment
import javax.inject.Inject


class MainActivity : AppCompatActivity(), NavigationViewNavigator {
    private lateinit var binding: ActivityMainBinding
    private lateinit var switcher: SwitchCompat

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val vModel: ActivityViewModel by viewModels { factory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        switcher = binding.navigator.menu
            .findItem(R.id.dark_theme_switcher)
            .actionView
            ?.findViewById(R.id.switcher)
            ?: throw IllegalArgumentException("unknown view id")
        setContentView(binding.root)


        AppCompatDelegate.setDefaultNightMode(vModel.getThemeMode())
        if (vModel.getThemeMode() == 2) switcher.isChecked = true

        switcher.setOnCheckedChangeListener { _, isChecked ->
            vModel.saveThemeMode(isChecked)
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }


        binding.navigator.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> menuHome()
                R.id.menu_about_style -> menuAboutStyle()
                R.id.menu_about_filter -> menuAboutFilter()
                R.id.menu_history -> menuHistory()
                R.id.dark_theme_switcher -> menuDarkThemeSwitcher()
                R.id.site -> menuSite()
                R.id.about_balaboba -> menuAboutBalaboba()
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun menuHome() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.MainFr, MainFragment())
            .commit()
        binding.drawer.close()
    }

    override fun menuAboutStyle() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.MainFr, StylesInfoFragment())
            .commit()
        binding.drawer.close()
    }

    override fun menuAboutFilter() {
        this.showLongToast(R.string.about_filter)
    }

    override fun menuHistory() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.MainFr, HistoryFragment()).commit()
        binding.drawer.close()
    }

    override fun menuDarkThemeSwitcher() {
        switcher.toggle()
    }

    override fun menuSite() {
        startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse("https://yandex.ru/lab/yalm"))
        )
    }

    override fun menuAboutBalaboba() {
        startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse("https://yandex.ru/lab/yalm-howto"))
        )
    }
}