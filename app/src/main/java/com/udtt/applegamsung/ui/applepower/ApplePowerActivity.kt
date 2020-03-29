package com.udtt.applegamsung.ui.applepower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.udtt.applegamsung.databinding.ActivityApplePowerBinding

class ApplePowerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityApplePowerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
