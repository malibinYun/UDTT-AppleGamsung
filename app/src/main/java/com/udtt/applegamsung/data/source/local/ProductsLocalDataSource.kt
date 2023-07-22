package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.AppleProductsDao
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.local.mapper.toAppleProductEntity
import com.udtt.applegamsung.data.local.mapper.toProduct
import com.udtt.applegamsung.data.source.ProductsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created By Yun Hyeok
 * on 3월 16, 2020
 */

class ProductsLocalDataSource(
    private val appleProductsDao: AppleProductsDao,
    private val mainCoroutineScope: CoroutineScope,
    private val ioCoroutineScope: CoroutineScope,
) : ProductsDataSource {

    override fun getProducts(categoryId: String, callback: (products: List<Product>) -> Unit) {
        ioCoroutineScope.launch {
            val products = appleProductsDao.getProductsByCategoryId(categoryId)
                .map { it.toProduct() }
            mainCoroutineScope.launch { callback(products) }
        }
    }

    override fun saveProducts(products: List<Product>) {
        ioCoroutineScope.launch {
            appleProductsDao.insertProducts(products.map { it.toAppleProductEntity() })
        }
    }
}
