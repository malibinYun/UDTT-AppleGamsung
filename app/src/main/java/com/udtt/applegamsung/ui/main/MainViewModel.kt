package com.udtt.applegamsung.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.entity.SelectedProduct
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_NICKNAME
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_PRODUCTS

/**
 * Created By Yun Hyeok
 * on 3월 24, 2020
 */

class MainViewModel : ViewModel() {

    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int>
        get() = _currentPage

    private val _selectedCategoryId = MutableLiveData<String>()
    val selectedCategoryId: LiveData<String>
        get() = _selectedCategoryId

    private val _selectedProducts = MutableLiveData<List<SelectedProduct>>()
    val selectedProducts: LiveData<List<SelectedProduct>>
        get() = _selectedProducts

    init {
        _currentPage.value = FRAGMENT_NICKNAME
        _selectedProducts.value = emptyList()
    }

    fun movePageTo(pageNum: Int) {
        _currentPage.value = pageNum
    }

    fun selectCategory(categoryId: String) {
        _selectedProducts.value = emptyList()
        _selectedCategoryId.value = categoryId
        movePageTo(FRAGMENT_PRODUCTS)
    }

    fun clearSelectedProducts() {
        _selectedProducts.value = emptyList()
    }

    fun handleHasAppleCare(product: Product) {
        val currentSelectedProducts = getCurrentSelectedProducts()
        val selectedProduct = currentSelectedProducts.find { it.product.id == product.id }
            ?: throw IllegalStateException("선택된 제품이 애플케어 선택리스트에 없을 수 없음.")
        val position = currentSelectedProducts.indexOf(selectedProduct)
        currentSelectedProducts[position] =
            SelectedProduct(selectedProduct.product, !selectedProduct.hasAppleCare)
        _selectedProducts.value = currentSelectedProducts
    }

    fun handleSelectedProduct(product: Product) {
        val handledProducts = createSelectHandledProducts(product)
        _selectedProducts.value = handledProducts.sortedBy { it.product.name }
    }

    private fun createSelectHandledProducts(product: Product): List<SelectedProduct> {
        val currentSelectedProducts = getCurrentSelectedProducts()
        val selectedProduct = currentSelectedProducts.find { it.product.id == product.id }

        return if (selectedProduct == null) currentSelectedProducts.apply { add(product.toSelectedProduct()) }
        else currentSelectedProducts.apply { remove(selectedProduct) }
    }

    private fun getCurrentSelectedProducts(): MutableList<SelectedProduct> {
        return _selectedProducts.value?.toMutableList()
            ?: throw IllegalStateException("List<Product> 가 null일 수 없음")
    }
}