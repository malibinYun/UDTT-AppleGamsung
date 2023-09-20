package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.source.CategoriesDataSource
import com.udtt.applegamsung.domain.repository.CategoriesRepository
import com.udtt.applegamsung.util.getOrEmpty

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

class DefaultCategoriesRepository(
    private val remoteCategoriesDataSource: CategoriesDataSource,
    private val localCategoriesDataSource: CategoriesDataSource,
) : CategoriesRepository {

    override suspend fun getCategories(): Result<List<Category>> {
        val localCategories = localCategoriesDataSource.getCategories()
        if (localCategories.getOrEmpty().isNotEmpty()) {
            return localCategories
        }
        return remoteCategoriesDataSource.getCategories()
            .onSuccess { saveCategories(it) }
    }

    override suspend fun saveCategories(categories: List<Category>): Result<Unit> {
        return localCategoriesDataSource.saveCategories(categories)
    }
}
