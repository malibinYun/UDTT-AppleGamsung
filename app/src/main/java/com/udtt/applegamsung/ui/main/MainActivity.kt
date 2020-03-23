package com.udtt.applegamsung.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.udtt.applegamsung.databinding.ActivityMainBinding
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vpMain.adapter = MainViewPagerAdapter(this)
    }
}
