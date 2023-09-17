package com.udtt.applegamsung.data.source

import com.udtt.applegamsung.data.entity.Product

/**
 * Created By Yun Hyeok
 * on 3월 16, 2020
 */

interface ProductsDataSource {

    suspend fun getProducts(categoryId: String): Result<List<Product>>

    suspend fun saveProducts(products: List<Product>): Result<Unit>
}
