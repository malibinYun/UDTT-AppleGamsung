package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.entity.DisplayedProduct
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.source.AppleBoxItemsDataSource
import com.udtt.applegamsung.data.source.ProductsDataSource
import com.udtt.applegamsung.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultProductsRepository(
    private val productsRemoteDataSource: ProductsDataSource,
    private val productsLocalDataSource: ProductsDataSource,
    private val appleBoxItemsLocalDataSource: AppleBoxItemsDataSource
) : ProductsRepository {

    override fun getProducts(categoryId: String, callback: (products: List<Product>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            productsLocalDataSource.getProducts(categoryId)
                .onSuccess {
                    if (it.isEmpty()) getProductsFromRemoteDataSource(categoryId, callback)
                    else callback(it)
                }
                .onFailure { getProductsFromRemoteDataSource(categoryId, callback) }
        }
    }

    override fun saveProducts(products: List<Product>) {
        CoroutineScope(Dispatchers.IO).launch {
            productsLocalDataSource.saveProducts(products)
        }
    }

    private fun getProductsFromRemoteDataSource(
        categoryId: String,
        callback: (products: List<Product>) -> Unit
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            productsRemoteDataSource.getProducts(categoryId)
                .onSuccess {
                    callback(it)
                    saveProducts(it)
                }
                .onFailure { throw it }
        }
    }

    // 워 이 개극혐 소스를 어케 바꿔야하지 ? 콜백헬 개극혐이넹... RxJava를 할줄 모르는게 한이다...
    override fun getDisplayedProducts(
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
        CoroutineScope(Dispatchers.Main).launch {
            appleBoxItemsLocalDataSource.getAppleBoxItems()
                .onSuccess { items ->
                    items.forEach { changeStateInAppleBox(products, it) }
                    callback(products)
                }
                .onFailure { throw it }
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
