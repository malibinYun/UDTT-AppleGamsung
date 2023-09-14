package com.udtt.applegamsung.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.remote.firestore.getDocumentSnapshots
import com.udtt.applegamsung.data.source.CategoriesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

class CategoriesRemoteDataSource(
    private val firestore: FirebaseFirestore
) : CategoriesDataSource {

    override fun getCategories(callback: (categories: List<Category>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val categories = firestore.collection(PathCategory)
                .getDocumentSnapshots()
                .map {
                    Category(
                        id = it.id,
                        name = it.getString("name").orEmpty(),
                        index = it.getLong("index")?.toInt() ?: 0,
                    )
                }
            callback(categories)
        }
    }

    override fun saveCategories(categories: List<Category>) {
        throw UnsupportedOperationException("Do not call saveCategories() in remoteSource")
    }

    companion object {
        private const val PathCategory = "categories"
    }
}
