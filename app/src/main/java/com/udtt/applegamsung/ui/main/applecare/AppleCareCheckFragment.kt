package com.udtt.applegamsung.ui.main.applecare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.data.entity.DisplayedProduct
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.databinding.FragmentCheckApplecareBinding
import com.udtt.applegamsung.ui.main.MainViewModel
import com.udtt.applegamsung.ui.main.products.ProductClickListener
import com.udtt.applegamsung.ui.main.products.ProductsAdapter
import org.koin.android.ext.android.inject

/**
 * Created By Yun Hyeok
 * on 3월 24, 2020
 */

class AppleCareCheckFragment : Fragment(), ProductClickListener {

    private val viewModelFactory: ViewModelProvider.Factory by inject()
    private lateinit var appleCareCheckViewModel: AppleCareCheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val productsAdapter = createProductsAdapter()
        val binding = FragmentCheckApplecareBinding.inflate(inflater).apply {
            rvProducts.adapter = productsAdapter
            lifecycleOwner = this@AppleCareCheckFragment
        }
        val mainViewModel =
            ViewModelProvider(activity!!, viewModelFactory)[MainViewModel::class.java]

        appleCareCheckViewModel =
            ViewModelProvider(this, viewModelFactory)[AppleCareCheckViewModel::class.java]

        mainViewModel.selectedProducts.observe(this, Observer { products ->
            //productsAdapter.submitList(products.map { DisplayedProduct(it) }.toMutableList())
            appleCareCheckViewModel.initSelectedProducts(products)
        })

        appleCareCheckViewModel.savedSelectedProducts.observe(this, Observer {

        })

        return binding.root
    }

    override fun onProductClick(displayedProduct: DisplayedProduct) {
        // appleCareCheckViewModel.handleAppleCareProduct(product, isSelected)
    }

    private fun createProductsAdapter(): ProductsAdapter {
        return ProductsAdapter().apply { setItemClickListener(this@AppleCareCheckFragment) }
    }

    companion object {
        private var INSTANCE: AppleCareCheckFragment? = null
        fun getInstance() = INSTANCE
            ?: AppleCareCheckFragment().apply { INSTANCE = this }
    }
}