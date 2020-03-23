package com.udtt.applegamsung.ui.main.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.repository.ProductsRepository
import com.udtt.applegamsung.util.BaseViewModel

class ProductsViewModel(
    private val productsRepository: ProductsRepository
) : BaseViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    fun loadProductsOf(categoryId: String) {
        _isLoading.value = true
        productsRepository.getProducts(categoryId) {
            _products.value = it
            _isLoading.value = false
        }
    }
}