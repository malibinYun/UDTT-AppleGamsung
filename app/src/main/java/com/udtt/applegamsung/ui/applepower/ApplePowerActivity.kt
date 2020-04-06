package com.udtt.applegamsung.ui.applepower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.data.repository.TestResultsRepository
import com.udtt.applegamsung.databinding.ActivityApplePowerBinding
import com.udtt.applegamsung.ui.util.BaseActivity
import com.udtt.applegamsung.util.log
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


//        val testResultsRepository: TestResultsRepository by inject()
//        testResultsRepository.getApplePower(200) {
//            log(it.toString())
//        }

        binding.imgApplePower.translationY = 200f

        binding.chipMacPro.isSelected = true
        binding.chipMacMini.isSelected = true
        binding.chipImacPro.isSelected = true
        binding.chipImac.isSelected = true
        binding.chipMacbookAir.isSelected = true
        binding.chipMacbookPro.isSelected = true
        binding.chipMacbook.isSelected = true
        binding.chipIphone.isSelected = true
        binding.chipAppleWatch.isSelected = true
        binding.chipIpad.isSelected = true
        binding.chipAirpods.isSelected = true
        binding.chipIpod.isSelected = true
    }

    private fun initView(binding: ActivityApplePowerBinding) {
        initAdmob(binding.banner)
    }
}
