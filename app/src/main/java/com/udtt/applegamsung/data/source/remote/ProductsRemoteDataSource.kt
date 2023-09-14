package com.udtt.applegamsung.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.remote.firestore.getDocumentSnapshots
import com.udtt.applegamsung.data.source.ProductsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created By Yun Hyeok
 * on 3월 16, 2020
 */

class ProductsRemoteDataSource(
    private val firestore: FirebaseFirestore
) : ProductsDataSource {

    override fun getProducts(categoryId: String, callback: (products: List<Product>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val products = firestore.collection(PathCategory)
                .document(categoryId)
                .collection(PathProducts)
                .getDocumentSnapshots()
                .map {
                    Product(
                        id = it.id,
                        name = it.getString("name").orEmpty(),
                        score = it.getLong("score")?.toInt() ?: 0,
                        categoryIndex = it.getLong("categoryIndex")?.toInt() ?: 0,
                        categoryId = it.getString("categoryId").orEmpty(),
                    )
                }
            callback(products)
        }
    }

    override fun saveProducts(products: List<Product>) {
        throw UnsupportedOperationException("Do not call saveProducts() in remoteSource")
    }

    companion object {
        private const val PathCategory = "categories"
        private const val PathProducts = "products"
    }
}
