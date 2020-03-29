package com.udtt.applegamsung.ui.applepower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.databinding.ActivityApplePowerBinding
import org.koin.android.ext.android.inject

class ApplePowerActivity : AppCompatActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityApplePowerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val applePowerViewModel =
            ViewModelProvider(this, viewModelFactory)[ApplePowerViewModel::class.java]

        binding.applePowerViewModel = applePowerViewModel
        binding.lifecycleOwner = this
    }
}
