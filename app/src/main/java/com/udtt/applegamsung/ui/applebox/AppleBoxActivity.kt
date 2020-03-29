package com.udtt.applegamsung.ui.applebox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.R
import com.udtt.applegamsung.databinding.ActivityAppleBoxBinding
import org.koin.android.ext.android.inject

class AppleBoxActivity : AppCompatActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAppleBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appleBoxViewModel =
            ViewModelProvider(this, viewModelFactory)[AppleBoxViewModel::class.java]

        val adapter = AppleBoxAdapter()
        binding.lifecycleOwner = this
        binding.rvAppleBoxItems.adapter = adapter

        appleBoxViewModel.appleBoxItems.observe(this, Observer {
            adapter.submitList(it)
        })

    }
}
