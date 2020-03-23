package com.udtt.applegamsung.ui.main.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.databinding.FragmentProductsBinding
import org.koin.android.ext.android.inject

class ProductsFragment : Fragment() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProductsBinding.inflate(inflater)

        return binding.root
    }

    companion object {
        private var INSTANCE: ProductsFragment? = null
        fun getInstance() = INSTANCE
            ?: ProductsFragment().apply { INSTANCE = this }
    }
}