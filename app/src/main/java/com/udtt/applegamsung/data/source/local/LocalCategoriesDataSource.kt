package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.AppleProductCategoriesDao
import com.udtt.applegamsung.domain.model.category.Category
import com.udtt.applegamsung.data.local.mapper.toAppleProductCategoryEntity
import com.udtt.applegamsung.data.local.mapper.toCategory
import com.udtt.applegamsung.data.source.CategoriesDataSource

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

class LocalCategoriesDataSource(
    private val appleProductCategoriesDao: AppleProductCategoriesDao,
) : CategoriesDataSource {

    override suspend fun getCategories(): Result<List<Category>> {
        return runCatching { appleProductCategoriesDao.getCategories() }
            .map { categoryEntities -> categoryEntities.map { it.toCategory() } }
    }

    override suspend fun saveCategories(categories: List<Category>): Result<Unit> {
        return runCatching {
            appleProductCategoriesDao.insertCategories(
                categories.map { it.toAppleProductCategoryEntity() },
            )
        }
    }
}
