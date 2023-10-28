package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.AppleProductsDao
import com.udtt.applegamsung.data.local.mapper.toAppleProductEntity
import com.udtt.applegamsung.data.local.mapper.toProduct
import com.udtt.applegamsung.data.source.ProductsDataSource
import com.udtt.applegamsung.domain.model.product.Product
import com.udtt.applegamsung.domain.model.product.Products
import com.udtt.applegamsung.util.mapList

/**
 * Created By Yun Hyeok
 * on 3월 16, 2020
 */

class LocalProductsDataSource(
    private val appleProductsDao: AppleProductsDao,
) : ProductsDataSource {

    override suspend fun getProducts(categoryId: String): Result<Products> {
        return runCatching { appleProductsDao.getProductsByCategoryId(categoryId) }
            .mapList { it.toProduct() }
            .map { Products(it) }
    }

    override suspend fun saveProducts(products: List<Product>): Result<Unit> {
        return runCatching {
            appleProductsDao.insertProducts(
                products.map { it.toAppleProductEntity() },
            )
        }
    }
}
