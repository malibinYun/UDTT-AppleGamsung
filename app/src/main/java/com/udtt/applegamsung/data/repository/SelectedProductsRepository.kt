package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.SelectedProduct
import com.udtt.applegamsung.data.source.SelectedProductsDataSource

class SelectedProductsRepository(
    private val selectedProductsLocalDataSource: SelectedProductsDataSource
) : SelectedProductsDataSource {

    override fun getSelectedProducts(callback: (selectedProducts: List<SelectedProduct>) -> Unit) {
        selectedProductsLocalDataSource.getSelectedProducts {
            callback(it)
        }
    }

    override fun saveSelectedProducts(selectedProducts: List<SelectedProduct>) {
        selectedProductsLocalDataSource.saveSelectedProducts(selectedProducts)
    }

    override fun removeSelectedProduct(productToRemove: SelectedProduct) {
        selectedProductsLocalDataSource.removeSelectedProduct(productToRemove)
    }
}