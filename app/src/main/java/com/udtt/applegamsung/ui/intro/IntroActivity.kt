package com.udtt.applegamsung.ui.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udtt.applegamsung.R
import com.udtt.applegamsung.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
