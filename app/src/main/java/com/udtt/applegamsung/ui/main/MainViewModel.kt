package com.udtt.applegamsung.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_NICKNAME
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_PRODUCTS

/**
 * Created By Yun Hyeok
 * on 3월 24, 2020
 */

class MainViewModel : ViewModel() {

    private val _currentPage = MutableLiveData<Int>().apply { value = FRAGMENT_NICKNAME }
    val currentPage: LiveData<Int>
        get() = _currentPage

    private val _selectedCategoryId = MutableLiveData<String>()
    val selectedCategoryId: LiveData<String>
        get() = _selectedCategoryId

    private val _selectedProducts = MutableLiveData<List<Product>>().apply { value = emptyList() }
    val selectedProducts: LiveData<List<Product>>
        get() = _selectedProducts

    fun movePageTo(pageNum: Int) {
        _currentPage.value = pageNum
    }

    fun selectCategory(categoryId: String) {
        _selectedCategoryId.value = categoryId
        movePageTo(FRAGMENT_PRODUCTS)
    }

    fun clearSelectedProducts() {
        _selectedProducts.value = emptyList()
    }

    fun handleSelectedProduct(product: Product) {
        val handledProducts = createSelectHandledProducts(product)
        _selectedProducts.value = handledProducts.sortedBy { it.name }
    }

    private fun createSelectHandledProducts(product: Product): List<Product> {
        val currentSelectedProduct = getCurrentSelectedProducts()
        val position = currentSelectedProduct.indexOf(product)

        return if (position == NOT_FOUND) currentSelectedProduct.apply { add(product) }
        else currentSelectedProduct.apply { remove(product) }
    }

    private fun getCurrentSelectedProducts(): MutableList<Product> {
        return _selectedProducts.value?.toMutableList()
            ?: throw IllegalStateException("List<Product> 가 null일 수 없음")
    }

    companion object {
        private const val NOT_FOUND = -1
    }

}