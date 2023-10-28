package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.DisplayedProduct
import com.udtt.applegamsung.data.source.AppleBoxItemsDataSource
import com.udtt.applegamsung.data.source.ProductsDataSource
import com.udtt.applegamsung.domain.model.product.Product
import com.udtt.applegamsung.domain.model.product.Products
import com.udtt.applegamsung.domain.repository.ProductsRepository
import com.udtt.applegamsung.util.getOrEmpty
import com.udtt.applegamsung.util.mapList

class DefaultProductsRepository(
    private val remoteProductsDataSource: ProductsDataSource,
    private val localProductsDataSource: ProductsDataSource,
    private val localAppleBoxItemsDataSource: AppleBoxItemsDataSource,
) : ProductsRepository {

    override suspend fun getProducts(categoryId: String): Result<Products> {
        localProductsDataSource.getProducts(categoryId)
            .onSuccess { if (it.isNotEmpty()) return Result.success(it) }
            .onFailure { return Result.failure(it) }

        return remoteProductsDataSource.getProducts(categoryId)
            .onSuccess { saveProducts(it.getValues()) }
    }

    override suspend fun saveProducts(products: List<Product>): Result<Unit> {
        return localProductsDataSource.saveProducts(products)
    }

    override suspend fun getDisplayedProducts(categoryId: String): Result<List<DisplayedProduct>> {
        val displayedProducts = getProducts(categoryId)
            .map { it.getValues() }
            .mapList { product -> DisplayedProduct(product) }
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
