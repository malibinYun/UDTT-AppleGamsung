package com.udtt.applegamsung.domain.repository

import com.udtt.applegamsung.data.entity.DisplayedProduct
import com.udtt.applegamsung.data.entity.Product

interface ProductsRepository {

    suspend fun getProducts(categoryId: String): Result<List<Product>>

    suspend fun saveProducts(products: List<Product>): Result<Unit>

    suspend fun getDisplayedProducts(categoryId: String): Result<List<DisplayedProduct>>
}
