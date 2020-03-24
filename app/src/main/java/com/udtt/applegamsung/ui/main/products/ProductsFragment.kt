package com.udtt.applegamsung.ui.main.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.databinding.FragmentProductsBinding
import com.udtt.applegamsung.ui.main.MainViewModel
import org.koin.android.ext.android.inject

class ProductsFragment : Fragment() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProductsBinding.inflate(inflater)

        val mainViewModel =
            ViewModelProvider(activity!!, viewModelFactory)[MainViewModel::class.java]
        val productsViewModel =
            ViewModelProvider(this, viewModelFactory)[ProductsViewModel::class.java]

        subscribeSelectedCategoryId(mainViewModel, productsViewModel)

        return binding.root
    }

    private fun subscribeSelectedCategoryId(
        mainViewModel: MainViewModel,
        productsViewModel: ProductsViewModel
    ) {
        mainViewModel.selectedCategoryId.observe(this, Observer { categoryId ->
            productsViewModel.loadProductsOf(categoryId)
        })
    }

    companion object {
        private var INSTANCE: ProductsFragment? = null
        fun getInstance() = INSTANCE
            ?: ProductsFragment().apply { INSTANCE = this }
    }
}