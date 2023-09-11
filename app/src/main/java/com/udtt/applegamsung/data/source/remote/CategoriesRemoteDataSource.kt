package com.udtt.applegamsung.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.source.CategoriesDataSource
import com.udtt.applegamsung.data.remote.firestore.CATEGORIES_PATH
import com.udtt.applegamsung.data.remote.firestore.getCollection
import com.udtt.applegamsung.data.remote.firestore.toCategories
import com.udtt.applegamsung.util.log
import com.udtt.applegamsung.util.showStackTrace
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

class CategoriesRemoteDataSource(
    private val fireStore: FirebaseFirestore
) : CategoriesDataSource {

    override fun getCategories(callback: (categories: List<Category>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            fireStore.getCollection(CATEGORIES_PATH).await().toCategories()
        }

        fireStore.getCollection(CATEGORIES_PATH)
            .addOnSuccessListener { callback(it.toCategories()) }
            .addOnFailureListener { log(it.showStackTrace()) }
    }

    override fun saveCategories(categories: List<Category>) {
        // No Needed
    }
}
