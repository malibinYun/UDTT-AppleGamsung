package com.udtt.applegamsung.ui.applepower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.databinding.ActivityApplePowerBinding
import com.udtt.applegamsung.ui.util.BaseActivity
import org.koin.android.ext.android.inject

class ApplePowerActivity : BaseActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityApplePowerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)

        val applePowerViewModel =
            ViewModelProvider(this, viewModelFactory)[ApplePowerViewModel::class.java]

        binding.applePowerViewModel = applePowerViewModel
        binding.lifecycleOwner = this
    }

    private fun initView(binding: ActivityApplePowerBinding) {
        initAdmob(binding.banner)
    }
}
