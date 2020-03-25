package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.SelectedProductsDao
import com.udtt.applegamsung.data.entity.SelectedProduct
import com.udtt.applegamsung.data.source.SelectedProductsDataSource
import com.udtt.applegamsung.util.AsyncExecutor

class SelectedProductsLocalDataSource(
    private val asyncExecutor: AsyncExecutor,
    private val selectedProductsDao: SelectedProductsDao
) : SelectedProductsDataSource {

    override fun getSelectedProducts(callback: (selectedProducts: List<SelectedProduct>) -> Unit) {
        asyncExecutor.ioThread.execute {
            val selectedProducts = selectedProductsDao.getSelectedProducts()
            asyncExecutor.mainThread.execute { callback(selectedProducts) }
        }
    }

    override fun saveSelectedProducts(selectedProducts: List<SelectedProduct>) {
        asyncExecutor.ioThread.execute {
            selectedProductsDao.insertSelectedProducts(selectedProducts)
        }
    }

    override fun removeSelectedProduct(productToRemove: SelectedProduct) {
        asyncExecutor.ioThread.execute {
            selectedProductsDao.deleteSelectedProduct(productToRemove)
        }
    }
}