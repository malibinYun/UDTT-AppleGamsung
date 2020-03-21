package com.udtt.applegamsung.ui.intro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.udtt.applegamsung.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
