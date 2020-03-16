package com.udtt.applegamsung.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udtt.applegamsung.R
import com.udtt.applegamsung.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
