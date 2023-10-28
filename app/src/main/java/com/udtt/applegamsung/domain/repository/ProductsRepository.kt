package com.udtt.applegamsung.domain.repository

import com.udtt.applegamsung.data.entity.DisplayedProduct
import com.udtt.applegamsung.domain.model.product.Product
import com.udtt.applegamsung.domain.model.product.Products

interface ProductsRepository {

    suspend fun getProducts(categoryId: String): Result<Products>

    suspend fun saveProducts(products: List<Product>): Result<Unit>

    suspend fun getDisplayedProducts(categoryId: String): Result<List<DisplayedProduct>>
}
