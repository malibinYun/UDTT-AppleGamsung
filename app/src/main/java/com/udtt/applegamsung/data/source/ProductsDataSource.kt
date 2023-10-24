package com.udtt.applegamsung.data.source

import com.udtt.applegamsung.domain.model.product.Product
import com.udtt.applegamsung.domain.model.product.Products

/**
 * Created By Yun Hyeok
 * on 3월 16, 2020
 */

interface ProductsDataSource {

    suspend fun getProducts(categoryId: String): Result<Products>

    suspend fun saveProducts(products: List<Product>): Result<Unit>
}
