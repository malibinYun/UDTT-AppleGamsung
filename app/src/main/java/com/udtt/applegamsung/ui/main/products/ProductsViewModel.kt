package com.udtt.applegamsung.ui.main.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udtt.applegamsung.data.entity.DisplayedProduct
import com.udtt.applegamsung.data.entity.Product
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

    fun handleSelectedProduct(product: Product) {
        _displayedProducts.value = createHandledDisplayedProducts(product)
    }

    private fun createHandledDisplayedProducts(product: Product): List<DisplayedProduct> {
        val currentDisplayedProduct = getCurrentDisplayedProducts()
        val selectedProduct = findDisplayedProductById(product.id)
        val position = findPositionOf(selectedProduct)
        val toggledDisplayedProduct = createSelectToggledProduct(selectedProduct)
        return currentDisplayedProduct.apply { set(position, toggledDisplayedProduct) }
    }

    private fun getCurrentDisplayedProducts(): MutableList<DisplayedProduct> {
        return _displayedProducts.value?.toMutableList()
            ?: throw IllegalStateException("displayedProducts는 null일 수 없음.")
    }

    private fun findDisplayedProductById(productId: String): DisplayedProduct {
        return getCurrentDisplayedProducts().find { it.product.id == productId }
            ?: throw IllegalStateException("제품이 리스트에 없을 수 없음")
    }

    private fun findPositionOf(item: DisplayedProduct): Int {
        val currentDisplayedProducts = getCurrentDisplayedProducts()
        return currentDisplayedProducts.indexOf(item)
    }

    private fun createSelectToggledProduct(old: DisplayedProduct): DisplayedProduct {
        val isSelected = !old.isSelected
        return DisplayedProduct(old.product, isSelected, old.isInAppleBox)
    }
}