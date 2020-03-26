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
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        initView(binding)
        subscribeCurrentPage(mainViewModel, binding)
    }

    override fun onBackPressed() {
        val currentPage = mainViewModel.currentPage.value
            ?: throw IllegalStateException("currentPage는 null일 수 없음")
        if (currentPage == FRAGMENT_NICKNAME) {
            super.onBackPressed()
            return
        }
        mainViewModel.movePageTo(currentPage - 1)
    }

    private fun initView(binding: ActivityMainBinding) {
        binding.vpMain.adapter = MainViewPagerAdapter(this)
        binding.vpMain.offscreenPageLimit = FRAGMENT_COUNTS

        (binding.vpMain.getChildAt(0) as RecyclerView)
            .overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun subscribeCurrentPage(mainViewModel: MainViewModel, binding: ActivityMainBinding) {
        mainViewModel.currentPage.observe(this, Observer {
            binding.vpMain.currentItem = it
        })
    }
}
