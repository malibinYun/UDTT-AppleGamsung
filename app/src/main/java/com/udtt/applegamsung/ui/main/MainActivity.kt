package com.udtt.applegamsung.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.udtt.applegamsung.databinding.ActivityMainBinding
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_COUNTS
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_NICKNAME
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        initView(binding)

        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        subscribeCurrentPage(binding)
    }

    override fun onBackPressed() {
        val currentPage = getCurrentPage()
        if (currentPage == FRAGMENT_NICKNAME) {
            super.onBackPressed()
            return
        }
        mainViewModel.movePageTo(currentPage - 1)
    }

    private fun initView(binding: ActivityMainBinding) {
        setContentView(binding.root)
        binding.vpMain.adapter = MainViewPagerAdapter(this)
        binding.vpMain.offscreenPageLimit = FRAGMENT_COUNTS
        binding.vpMain.isUserInputEnabled = false
    }

    private fun subscribeCurrentPage(binding: ActivityMainBinding) {
        mainViewModel.currentPage.observe(this, Observer {
            binding.vpMain.currentItem = it
        })
    }

    private fun getCurrentPage(): Int {
        return mainViewModel.currentPage.value
            ?: throw IllegalStateException("currentPage는 null일 수 없음")
    }
}
