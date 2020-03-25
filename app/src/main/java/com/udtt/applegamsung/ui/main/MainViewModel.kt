package com.udtt.applegamsung.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udtt.applegamsung.data.entity.Product
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

    fun handleSelectedProduct(product: Product, isSelected: Boolean) {
        val handledProducts = createSelectHandledProducts(product, isSelected)
        _selectedProducts.value = handledProducts.sortedBy { it.name }
    }

    private fun createSelectHandledProducts(product: Product, isSelected: Boolean): List<Product> {
        val selectedProducts = _selectedProducts.value?.toMutableList()
            ?: throw IllegalStateException("List<Product> 가 null일 수 없음")
        return if (isSelected) selectedProducts.apply { add(product) }
        else selectedProducts.apply { remove(product) }
    }

}