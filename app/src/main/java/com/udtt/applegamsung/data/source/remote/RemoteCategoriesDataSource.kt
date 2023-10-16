package com.udtt.applegamsung.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.remote.firestore.getDocumentSnapshots
import com.udtt.applegamsung.data.source.CategoriesDataSource
import com.udtt.applegamsung.domain.model.category.Category
import com.udtt.applegamsung.util.mapList

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

class RemoteCategoriesDataSource(
    private val firestore: FirebaseFirestore
) : CategoriesDataSource {

    override suspend fun getCategories(): Result<List<Category>> {
        return firestore.collection(PathCategory)
            .getDocumentSnapshots()
            .mapList {
                Category(
                    id = it.id,
                    name = it.getString("name").orEmpty(),
                    index = it.getLong("index")?.toInt() ?: 0,
                    imageUrl = it.getString("imageUrl").orEmpty(),
                )
            }
    }

    override suspend fun saveCategories(categories: List<Category>): Result<Unit> {
        return Result.failure(
            UnsupportedOperationException("Do not call saveCategories() in remoteSource")
        )
    }

    companion object {
        private const val PathCategory = "categories"
    }
}
