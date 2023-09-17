package com.udtt.applegamsung.domain.repository

import com.udtt.applegamsung.data.entity.DisplayedProduct
import com.udtt.applegamsung.data.entity.Product

interface ProductsRepository {

    fun getProducts(categoryId: String, callback: (products: List<Product>) -> Unit)

    fun saveProducts(products: List<Product>)

    fun getDisplayedProducts(
        categoryId: String,
        callback: (products: List<DisplayedProduct>) -> Unit
    )
}
