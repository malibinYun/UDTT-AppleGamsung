package com.udtt.applegamsung.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.entity.Product
import com.udtt.applegamsung.data.source.ProductsDataSource
import com.udtt.applegamsung.data.util.getCollection

/**
 * Created By Yun Hyeok
 * on 3월 16, 2020
 */
class ProductsRemoteDataSource(
    private val firestore: FirebaseFirestore
) : ProductsDataSource {
    override fun getProducts(categoryIndex: Int, callback: (products: List<Product>) -> Unit) {
        firestore.collection("categories").document("")
            .collection("products").get()

    }

    override fun saveProducts(products: List<Product>) {
        // No Needed
    }
}