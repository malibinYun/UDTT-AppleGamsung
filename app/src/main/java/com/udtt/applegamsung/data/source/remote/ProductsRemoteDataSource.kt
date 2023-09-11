package com.udtt.applegamsung.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.remote.firestore.PRODUCTS_PATH
import com.udtt.applegamsung.data.remote.firestore.getCategoryDocumentById
import com.udtt.applegamsung.data.remote.firestore.getCollection
import com.udtt.applegamsung.data.remote.firestore.toProductsOf
import com.udtt.applegamsung.data.source.ProductsDataSource
import com.udtt.applegamsung.util.log
import com.udtt.applegamsung.util.showStackTrace

/**
 * Created By Yun Hyeok
 * on 3월 16, 2020
 */

class ProductsRemoteDataSource(
    private val firestore: FirebaseFirestore
) : ProductsDataSource {

    override fun getProducts(categoryId: String, callback: (products: List<Product>) -> Unit) {
        firestore.getCategoryDocumentById(categoryId)
            .getCollection(PRODUCTS_PATH)
            .addOnSuccessListener { callback(it.toProductsOf(categoryId)) }
            .addOnFailureListener { log(it.showStackTrace()) }
    }

    override fun saveProducts(products: List<Product>) {
        // No Needed
    }
}
