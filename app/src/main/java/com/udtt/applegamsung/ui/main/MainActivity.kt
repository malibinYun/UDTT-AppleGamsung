package com.udtt.applegamsung.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.databinding.ActivityMainBinding
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_COUNTS
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        initView(binding)
        subscribeCurrentPage(mainViewModel, binding)
    }

    private fun initView(binding: ActivityMainBinding) {
        binding.vpMain.adapter = MainViewPagerAdapter(this)
        binding.vpMain.offscreenPageLimit = FRAGMENT_COUNTS
    }

    private fun subscribeCurrentPage(mainViewModel: MainViewModel, binding: ActivityMainBinding) {
        mainViewModel.currentPage.observe(this, Observer {
            binding.vpMain.currentItem = it
        })
    }
}
