package com.udtt.applegamsung.ui.main.applecare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.entity.SelectedProduct
import com.udtt.applegamsung.data.repository.SelectedProductsRepository
import com.udtt.applegamsung.util.BaseViewModel
import com.udtt.applegamsung.util.log

/**
 * Created By Yun Hyeok
 * on 3월 24, 2020
 */

class AppleCareCheckViewModel(
    private val selectedProductsRepository: SelectedProductsRepository
) : BaseViewModel() {

    private val selectedProducts = mutableListOf<SelectedProduct>()

    private val _savedSelectedProducts = MutableLiveData<List<SelectedProduct>>()
    val savedSelectedProducts: LiveData<List<SelectedProduct>>
        get() = _savedSelectedProducts

    fun initSelectedProducts(products: List<Product>) {
        val selectedProducts = products.map { product -> SelectedProduct(product) }
        this.selectedProducts.clear()
        this.selectedProducts.addAll(selectedProducts)
    }

    fun handleAppleCareProduct(product: Product, isSelected: Boolean) {
        val selectedProduct = selectedProducts.find { it.product == product }
            ?: throw IllegalArgumentException("존재하지 않는 product")
        selectedProduct.hasAppleCare = isSelected
    }

    fun saveSelectedProducts() {
        val currentSelectedProducts = ArrayList(selectedProducts)
        selectedProductsRepository.saveSelectedProducts(currentSelectedProducts)
        _savedSelectedProducts.value = currentSelectedProducts
        clearSelectedProducts()
    }

    private fun clearSelectedProducts() {
        selectedProducts.clear()
    }
}