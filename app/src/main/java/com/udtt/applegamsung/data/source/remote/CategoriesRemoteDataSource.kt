package com.udtt.applegamsung.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.source.CategoriesDataSource
import com.udtt.applegamsung.data.util.getCollection
import com.udtt.applegamsung.data.util.toCategories
import com.udtt.applegamsung.util.log
import com.udtt.applegamsung.util.showStackTrace

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

class CategoriesRemoteDataSource(
    private val fireStore: FirebaseFirestore
) : CategoriesDataSource {

    override fun getCategories(callback: (categories: List<Category>) -> Unit) {
        fireStore.getCollection("categories")
            .addOnSuccessListener { callback(it.toCategories()) }
            .addOnFailureListener { log(it.showStackTrace()) }
    }

    override fun saveCategories(categories: List<Category>) {
        // No Needed
    }
}