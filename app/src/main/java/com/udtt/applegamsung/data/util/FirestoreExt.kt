package com.udtt.applegamsung.data.util

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.entity.Product

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

fun DocumentReference.getCollection(collectionPath: String): Task<QuerySnapshot> {
    return this.collection(collectionPath).get()
}

fun FirebaseFirestore.getCollection(collectionPath: String): Task<QuerySnapshot> {
    return this.collection(collectionPath).get()
}

fun FirebaseFirestore.getCategoryDocumentById(categoryId: String): DocumentReference {
    return this.collection(CATEGORIES_PATH).document(categoryId)
}

fun QuerySnapshot.toCategories(): List<Category> {
    return this.documents.map {
        val category = it.toObject(Category::class.java) ?: throw CANNOT_CONVERT_EXCEPTION
        category.apply { id = it.id }
    }
}

fun QuerySnapshot.toProductsOf(categoryId: String): List<Product> {
    return this.documents.map {
        val product = it.toObject(Product::class.java) ?: throw CANNOT_CONVERT_EXCEPTION
        product.apply { this.categoryId = categoryId }
    }.sortedBy { it.name }
}

fun QuerySnapshot.toApplePowers(): List<ApplePower> {
    return this.documents.map {
        val applePower = it.toObject(ApplePower::class.java) ?: throw CANNOT_CONVERT_EXCEPTION
        applePower.apply { this.id = it.id }
    }.sortedBy { it.name }
}

const val CATEGORIES_PATH = "categories"
const val PRODUCTS_PATH = "products"
const val TEST_RESULTS_PATH = "testResults"
const val APPLE_POWER_PATH = "ApplePower"

private val CANNOT_CONVERT_EXCEPTION = IllegalStateException("객체를 변환할 수 없음")