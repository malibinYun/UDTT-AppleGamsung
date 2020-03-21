package com.udtt.applegamsung.ui.intro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.udtt.applegamsung.databinding.ActivityIntroBinding
import org.koin.android.ext.android.inject

class IntroActivity : AppCompatActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this, viewModelFactory)[IntroViewModel::class.java]
        viewModel.checkDeviceId()

        val adRequest = AdRequest.Builder().build()
        binding.banner.loadAd(adRequest)


        binding.btnStart.setOnClickListener {
            viewModel.checkDeviceId()
        }
    }
}
