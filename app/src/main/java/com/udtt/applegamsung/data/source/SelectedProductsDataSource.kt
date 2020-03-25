package com.udtt.applegamsung.data.source

import com.udtt.applegamsung.data.entity.SelectedProduct

interface SelectedProductsDataSource {

    fun getSelectedProducts(callback: (selectedProducts: List<SelectedProduct>) -> Unit)

    fun saveSelectedProducts(selectedProducts: List<SelectedProduct>)

    fun removeSelectedProduct(productToRemove: SelectedProduct)

}