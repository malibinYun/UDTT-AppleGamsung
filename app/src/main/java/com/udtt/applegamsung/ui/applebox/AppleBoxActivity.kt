package com.udtt.applegamsung.ui.applebox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udtt.applegamsung.R
import com.udtt.applegamsung.databinding.ActivityAppleBoxBinding

class AppleBoxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAppleBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
