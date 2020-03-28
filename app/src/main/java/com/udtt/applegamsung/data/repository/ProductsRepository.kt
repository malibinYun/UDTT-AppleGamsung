package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.entity.DisplayedProduct
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.source.AppleBoxItemsDataSource
import com.udtt.applegamsung.data.source.ProductsDataSource

class ProductsRepository(
    private val productsRemoteDataSource: ProductsDataSource,
    private val productsLocalDataSource: ProductsDataSource,
    private val appleBoxItemsLocalDataSource: AppleBoxItemsDataSource
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

    // 워 이 개극혐 소스를 어케 바꿔야하지 ? 콜백헬 개극혐이넹... RxJava를 할줄 모르는게 한이다...
    fun getDisplayedProducts(
        categoryId: String,
        callback: (products: List<DisplayedProduct>) -> Unit
    ) {
        getProducts(categoryId) { products ->
            val displayedProducts = products.map { DisplayedProduct(it) }
            checkInAppleBox(displayedProducts, callback)
        }
    }

    private fun checkInAppleBox(
        products: List<DisplayedProduct>,
        callback: (products: List<DisplayedProduct>) -> Unit
    ) {
        appleBoxItemsLocalDataSource.getAppleBoxItems { items ->
            items.forEach { item -> changeStateInAppleBox(products, item) }
            callback(products)
        }
    }

    private fun changeStateInAppleBox(
        products: List<DisplayedProduct>,
        appleBoxItem: AppleBoxItem
    ) {
        val productInAppleBox = products.find { it.product == appleBoxItem.product }
        productInAppleBox?.isInAppleBox = true
    }
}