package com.udtt.applegamsung.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.remote.firestore.getDocumentSnapshots
import com.udtt.applegamsung.data.source.ProductsDataSource
import com.udtt.applegamsung.domain.model.product.Product

/**
 * Created By Yun Hyeok
 * on 3월 16, 2020
 */

class RemoteProductsDataSource(
    private val firestore: FirebaseFirestore
) : ProductsDataSource {

    override suspend fun getProducts(categoryId: String): Result<List<Product>> {
        return firestore.collection(PathCategory)
            .document(categoryId)
            .collection(PathProducts)
            .getDocumentSnapshots()
            .map { documents ->
                documents.map {
                    Product(
                        id = it.id,
                        name = it.getString("name").orEmpty(),
                        score = it.getLong("score")?.toInt() ?: 0,
                        categoryIndex = it.getLong("categoryIndex")?.toInt() ?: 0,
                        categoryId = it.getString("categoryId").orEmpty(),
                        imageUrl = ""
                    )
                }
            }
    }

    override suspend fun saveProducts(products: List<Product>): Result<Unit> {
        return Result.failure(
            UnsupportedOperationException("Do not call saveProducts() in remoteSource")
        )
    }

    companion object {
        private const val PathCategory = "categories"
        private const val PathProducts = "products"
    }
}
