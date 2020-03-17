package com.udtt.applegamsung.ui.categories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.databinding.ActivityCategoriesBinding
import org.koin.android.ext.android.inject

class CategoriesActivity : AppCompatActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoriesViewModel =
            ViewModelProvider(this, viewModelFactory)[CategoriesViewModel::class.java]

        val categoriesAdapter =
            CategoriesAdapter()
        binding.categories.adapter = categoriesAdapter

        subscribeCategories(categoriesViewModel, categoriesAdapter)
    }

    private fun subscribeCategories(viewModel: CategoriesViewModel, adapter: CategoriesAdapter) {
        viewModel.categories.observe(this, Observer {
            adapter.initCategories(it)
        })
    }
}
