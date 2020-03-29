package com.udtt.applegamsung.ui.applebox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.databinding.ActivityAppleBoxBinding
import org.koin.android.ext.android.inject

class AppleBoxActivity : AppCompatActivity(), AppleBoxItemClickListener {

    private val viewModelFactory: ViewModelProvider.Factory by inject()
    private lateinit var appleBoxViewModel: AppleBoxViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAppleBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appleBoxViewModel = ViewModelProvider(this, viewModelFactory)[AppleBoxViewModel::class.java]

        val adapter = AppleBoxAdapter()
        adapter.setItemClickListener(this)

        binding.lifecycleOwner = this
        binding.appleBoxViewModel = appleBoxViewModel
        binding.rvAppleBoxItems.adapter = adapter

        appleBoxViewModel.appleBoxItems.observe(this, Observer {
            adapter.submitList(it)
        })

    }

    override fun onAppleBoxItemClick(item: AppleBoxItem) {
        appleBoxViewModel.deleteAppleBoxItem(item)
    }
}
