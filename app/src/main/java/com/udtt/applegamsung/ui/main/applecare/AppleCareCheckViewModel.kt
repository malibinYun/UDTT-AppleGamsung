package com.udtt.applegamsung.ui.main.applecare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.repository.AppleBoxItemsRepository
import com.udtt.applegamsung.util.BaseViewModel

/**
 * Created By Yun Hyeok
 * on 3월 24, 2020
 */

class AppleCareCheckViewModel(
    private val appleBoxItemsRepository: AppleBoxItemsRepository
) : BaseViewModel() {

    private val appleBoxItems = mutableListOf<AppleBoxItem>()

    private val _savedAppleBoxItems = MutableLiveData<List<AppleBoxItem>>()
    val savedAppleBoxItems: LiveData<List<AppleBoxItem>>
        get() = _savedAppleBoxItems

    fun initSelectedProducts(products: List<Product>) {
        val selectedProducts = products.map { product -> AppleBoxItem(product) }
        this.appleBoxItems.clear()
        this.appleBoxItems.addAll(selectedProducts)
    }

    fun handleAppleCareProduct(product: Product, isSelected: Boolean) {
        val selectedProduct = appleBoxItems.find { it.product == product }
            ?: throw IllegalArgumentException("존재하지 않는 product")
        selectedProduct.hasAppleCare = isSelected
    }

    fun saveSelectedProducts() {
        val currentSelectedProducts = ArrayList(appleBoxItems)
        appleBoxItemsRepository.saveAppleBoxItems(currentSelectedProducts)
        _savedAppleBoxItems.value = currentSelectedProducts
        clearSelectedProducts()
    }

    private fun clearSelectedProducts() {
        appleBoxItems.clear()
    }
}