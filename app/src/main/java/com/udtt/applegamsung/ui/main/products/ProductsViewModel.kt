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

    init {
        _displayedProducts.value = emptyList()
    }

    fun loadProductsOf(categoryId: String) {
        _isLoading.value = true
        productsRepository.getProducts(categoryId) { products ->
            _displayedProducts.value = products.map { DisplayedProduct(it) }
            _isLoading.value = false
        }
    }

    fun handleSelectedProduct(displayedProduct: DisplayedProduct) {
        _displayedProducts.value = createHandledDisplayedProducts(displayedProduct)
    }

    private fun createHandledDisplayedProducts(displayedProduct: DisplayedProduct): List<DisplayedProduct> {
        var position = findPositionOf(displayedProduct)
        val toggledDisplayedProduct = createSelectToggledDisplayedProduct(displayedProduct)
        if (position != NOT_FOUND) {
            return getCurrentDisplayedProducts().apply { set(position, toggledDisplayedProduct) }
        }
        position = findPositionOf(toggledDisplayedProduct)
        return getCurrentDisplayedProducts().apply { set(position, displayedProduct) }
    }

    private fun findPositionOf(item: DisplayedProduct): Int {
        val currentDisplayedProducts = getCurrentDisplayedProducts()
        return currentDisplayedProducts.indexOf(item)
    }

    private fun createSelectToggledDisplayedProduct(old: DisplayedProduct): DisplayedProduct {
        val isSelected = !old.isSelected
        return DisplayedProduct(old.product, isSelected, old.isInAppleBox)
    }

    private fun getCurrentDisplayedProducts(): MutableList<DisplayedProduct> {
        return _displayedProducts.value?.toMutableList()
            ?: throw IllegalStateException("displayedProducts는 null일 수 없음.")
    }

    companion object {
        private const val NOT_FOUND = -1
    }
}