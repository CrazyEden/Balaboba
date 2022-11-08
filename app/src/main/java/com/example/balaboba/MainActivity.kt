package com.example.balaboba

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.balaboba.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navigator.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_about->{

                    supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.MainFr,StylesInfoFragment())
                        .commit()
                }
                R.id.menu_filter->{
                    Toast.makeText(this@MainActivity,"Он что-то делает, а что - неизвестно",Toast.LENGTH_LONG).show()
                }
                R.id.menu_history->{

                }
            }
            return@setNavigationItemSelectedListener true
        }

    }


//    override fun onContextItemSelected(item: MenuItem): Boolean {
//
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//    }
}