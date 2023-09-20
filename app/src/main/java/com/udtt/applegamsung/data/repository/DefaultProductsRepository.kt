package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.DisplayedProduct
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.source.AppleBoxItemsDataSource
import com.udtt.applegamsung.data.source.ProductsDataSource
import com.udtt.applegamsung.domain.repository.ProductsRepository
import com.udtt.applegamsung.util.getOrEmpty

class DefaultProductsRepository(
    private val remoteProductsDataSource: ProductsDataSource,
    private val localProductsDataSource: ProductsDataSource,
    private val localAppleBoxItemsDataSource: AppleBoxItemsDataSource,
) : ProductsRepository {

    override suspend fun getProducts(categoryId: String): Result<List<Product>> {
        val localProducts = localProductsDataSource.getProducts(categoryId)
        if (localProducts.getOrEmpty().isNotEmpty()) {
            return localProducts
        }
        return remoteProductsDataSource.getProducts(categoryId)
            .onSuccess { saveProducts(it) }
    }

    override suspend fun saveProducts(products: List<Product>): Result<Unit> {
        return localProductsDataSource.saveProducts(products)
    }

    override suspend fun getDisplayedProducts(categoryId: String): Result<List<DisplayedProduct>> {
        val displayedProducts = getProducts(categoryId)
            .map { it.map { product -> DisplayedProduct(product) } }
            .onFailure { return Result.failure(it) }
            .getOrEmpty()

        localAppleBoxItemsDataSource.getAppleBoxItems()
            .onSuccess { items ->
                items.forEach { item ->
                    displayedProducts.find { it.product == item.product }?.isInAppleBox = true
                }
            }
            .onFailure { return Result.failure(it) }

        return Result.success(displayedProducts)
    }
}
