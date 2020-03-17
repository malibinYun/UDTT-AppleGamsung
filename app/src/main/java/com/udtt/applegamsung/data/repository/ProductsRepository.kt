package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.source.ProductsDataSource

class ProductsRepository(
    private val productsRemoteDataSource: ProductsDataSource,
    private val productsLocalDataSource: ProductsDataSource
) : ProductsDataSource {

    override fun getProducts(categoryId: String, callback: (products: List<Product>) -> Unit) {
        productsLocalDataSource.getProducts(categoryId) {
            if (it.isEmpty()) getProductsFromRemoteDataSource(categoryId, callback)
            else callback(it)
        }
    }

    override fun saveProducts(products: List<Product>) {
        productsLocalDataSource.saveProducts(products)
    }

    private fun getProductsFromRemoteDataSource(
        categoryId: String,
        callback: (products: List<Product>) -> Unit
    ) {
        productsRemoteDataSource.getProducts(categoryId) {
            callback(it)
            saveProducts(it)
        }
    }
}