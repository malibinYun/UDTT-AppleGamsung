package com.udtt.applegamsung.ui.main.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udtt.applegamsung.data.entity.DisplayedProduct
import com.udtt.applegamsung.data.repository.ProductsRepository
import com.udtt.applegamsung.util.BaseViewModel

class ProductsViewModel(
    private val productsRepository: ProductsRepository
) : BaseViewModel() {

    private val _displayedProducts = MutableLiveData<List<DisplayedProduct>>()
    val displayedProducts: LiveData<List<DisplayedProduct>>
        get() = _displayedProducts

    fun loadProductsOf(categoryId: String) {
        _isLoading.value = true
        productsRepository.getProducts(categoryId) { products ->
            _displayedProducts.value = products.map { DisplayedProduct(it) }
            _isLoading.value = false
        }
    }

}