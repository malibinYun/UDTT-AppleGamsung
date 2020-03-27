package com.udtt.applegamsung.ui.main.products

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.databinding.FragmentProductsBinding
import com.udtt.applegamsung.ui.main.MainViewModel
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_APPLECARE
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_CATEGORIES
import com.udtt.applegamsung.ui.util.BaseFragment

class ProductsFragment : BaseFragment(), ProductClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var productsViewModel: ProductsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainViewModel = ViewModelProvider(activity!!, viewModelFactory)[MainViewModel::class.java]
        productsViewModel = ViewModelProvider(this, viewModelFactory)[ProductsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val productsAdapter = createProductsAdapter()
        val binding = FragmentProductsBinding.inflate(inflater)
        initView(binding, productsAdapter)

        subscribeSelectedCategoryId()
        subscribeProducts(productsAdapter)
        return binding.root
    }

    override fun onProductClick(product: Product) {
//        if (displayedProduct.isInAppleBox) {
//            // do something
//            return
//        }
        mainViewModel.handleSelectedProduct(product)
        productsViewModel.handleSelectedProduct(product)
    }

    private fun initView(binding: FragmentProductsBinding, productsAdapter: ProductsAdapter) {
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.rvProducts.adapter = productsAdapter
        binding.btnBack.setOnClickListener { mainViewModel.movePageTo(FRAGMENT_CATEGORIES) }
        binding.btnNext.setOnClickListener { mainViewModel.movePageTo(FRAGMENT_APPLECARE) }
        initAdmob(binding.banner)
    }

    private fun createProductsAdapter(): ProductsAdapter {
        return ProductsAdapter().apply {
            setLifeCycleOwner(this@ProductsFragment)
            setItemClickListener(this@ProductsFragment)
        }
    }

    private fun subscribeSelectedCategoryId() {
        mainViewModel.selectedCategoryId.observe(this, Observer { categoryId ->
            productsViewModel.loadProductsOf(categoryId)
        })
    }

    private fun subscribeProducts(productsAdapter: ProductsAdapter) {
        productsViewModel.displayedProducts.observe(this, Observer { displayedProducts ->
            productsAdapter.submitList(displayedProducts)
        })
    }

    companion object {
        private var INSTANCE: ProductsFragment? = null
        fun getInstance() = INSTANCE
            ?: ProductsFragment().apply { INSTANCE = this }
    }
}