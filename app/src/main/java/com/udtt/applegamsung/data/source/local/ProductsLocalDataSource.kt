package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.ProductsDao
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.source.ProductsDataSource
import com.udtt.applegamsung.util.AsyncExecutor

/**
 * Created By Yun Hyeok
 * on 3월 16, 2020
 */

class ProductsLocalDataSource(
    private val asyncExecutor: AsyncExecutor,
    private val productsDao: ProductsDao
) : ProductsDataSource {

    override fun getProducts(categoryId: String, callback: (products: List<Product>) -> Unit) {
        asyncExecutor.ioThread.execute {
            val products = productsDao.getProductsByCategoryId(categoryId)
            asyncExecutor.mainThread.execute { callback(products) }
        }
    }

    override fun saveProducts(products: List<Product>) {
        asyncExecutor.ioThread.execute {
            productsDao.insertProducts(products)
        }
    }
}