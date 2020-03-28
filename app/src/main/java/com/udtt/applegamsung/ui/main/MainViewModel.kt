package com.udtt.applegamsung.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.entity.SelectedProduct
import com.udtt.applegamsung.data.repository.AppleBoxItemsRepository
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_CATEGORIES
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_NICKNAME
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_PRODUCTS

/**
 * Created By Yun Hyeok
 * on 3월 24, 2020
 */

class MainViewModel(
    private val appleBoxItemsRepository: AppleBoxItemsRepository
) : ViewModel() {

    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int>
        get() = _currentPage

    private val _selectedCategoryId = MutableLiveData<String>()
    val selectedCategoryId: LiveData<String>
        get() = _selectedCategoryId

    private val _selectedProducts = MutableLiveData<List<SelectedProduct>>()
    val selectedProducts: LiveData<List<SelectedProduct>>
        get() = _selectedProducts

    private val _savedProducts = MutableLiveData<List<AppleBoxItem>>()
    val savedProducts: LiveData<List<AppleBoxItem>>
        get() = _savedProducts

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

    fun handleHasAppleCare(product: Product) {
        _selectedProducts.value = createAppleCareHandledProducts(product)
    }

    private fun createAppleCareHandledProducts(product: Product): List<SelectedProduct> {
        val currentSelectedProducts = getCurrentSelectedProducts()
        val selectedProduct = findSelectedProductById(product.id)
            ?: throw IllegalStateException("선택된 제품이 애플케어 선택리스트에 없을 수 없음.")
        val position = currentSelectedProducts.indexOf(selectedProduct)
        val appleCareToggledProduct = createAppleCareToggledProduct(selectedProduct)
        return currentSelectedProducts.apply { set(position, appleCareToggledProduct) }
    }

    private fun createAppleCareToggledProduct(selectedProduct: SelectedProduct): SelectedProduct {
        return SelectedProduct(selectedProduct.product, !selectedProduct.hasAppleCare)
    }

    // 다시 item click을 displayed머시기를 넘기는걸로 하자. 여기서 사과박스에 있었는지를 막을 코드를 작성할 수 가없다.
    fun handleSelectedProduct(product: Product) {
        val handledProducts = createSelectHandledProducts(product)
        _selectedProducts.value = handledProducts.sortedBy { it.product.name }
    }

    private fun createSelectHandledProducts(product: Product): List<SelectedProduct> {
        val currentSelectedProducts = getCurrentSelectedProducts()
        val selectedProduct = findSelectedProductById(product.id)

        return if (selectedProduct == null) currentSelectedProducts.apply { add(product.toSelectedProduct()) }
        else currentSelectedProducts.apply { remove(selectedProduct) }
    }

    private fun getCurrentSelectedProducts(): MutableList<SelectedProduct> {
        return _selectedProducts.value?.toMutableList()
            ?: throw IllegalStateException("List<Product> 가 null일 수 없음")
    }

    private fun findSelectedProductById(productId: String): SelectedProduct? {
        return getCurrentSelectedProducts().find { it.product.id == productId }
    }

    fun boxSelectedProducts() {
        val selectedProducts = getCurrentSelectedProducts().map { it.toAppleBoxItem() }
        appleBoxItemsRepository.saveAppleBoxItems(selectedProducts)
        _savedProducts.value = selectedProducts
        movePageTo(FRAGMENT_CATEGORIES)
    }

}