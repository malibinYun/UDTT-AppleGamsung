package com.udtt.applegamsung.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.udtt.applegamsung.databinding.ActivityCategoriesBinding

class CategoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
