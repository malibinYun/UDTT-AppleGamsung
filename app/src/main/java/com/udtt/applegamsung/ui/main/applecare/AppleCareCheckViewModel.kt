package com.udtt.applegamsung.ui.main.applecare

import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.entity.SelectedProduct
import com.udtt.applegamsung.util.BaseViewModel
import com.udtt.applegamsung.util.log

/**
 * Created By Yun Hyeok
 * on 3월 24, 2020
 */
class AppleCareCheckViewModel(
) : BaseViewModel() {

    private val selectedProducts = mutableListOf<SelectedProduct>()

    fun initSelectedProducts(products: List<Product>) {
        val selectedProducts = products.map { product -> SelectedProduct(product) }
        this.selectedProducts.clear()
        this.selectedProducts.addAll(selectedProducts)
    }

    fun handleAppleCareProduct(product: Product, isSelected: Boolean) {
        val selectedProduct = selectedProducts.find { it.product == product }
            ?: throw IllegalArgumentException("존재하지 않는 product")
        selectedProduct.hasAppleCare = isSelected
        selectedProducts.forEach { log("${it.product.name} hasAppleCare? = ${it.hasAppleCare}") }
    }
}