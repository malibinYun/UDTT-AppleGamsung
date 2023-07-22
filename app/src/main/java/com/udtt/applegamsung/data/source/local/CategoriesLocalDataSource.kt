package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.AppleProductCategoriesDao
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.local.mapper.toAppleProductCategoryEntity
import com.udtt.applegamsung.data.local.mapper.toCategory
import com.udtt.applegamsung.data.source.CategoriesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

class CategoriesLocalDataSource(
    private val appleProductCategoriesDao: AppleProductCategoriesDao,
    private val mainCoroutineScope: CoroutineScope,
    private val ioCoroutineScope: CoroutineScope,
) : CategoriesDataSource {

    override fun getCategories(callback: (categories: List<Category>) -> Unit) {
        ioCoroutineScope.launch {
            val categories = appleProductCategoriesDao.getCategories().map { it.toCategory() }
            mainCoroutineScope.launch { callback(categories) }
        }
    }

    override fun saveCategories(categories: List<Category>) {
        ioCoroutineScope.launch {
            appleProductCategoriesDao.insertCategories(categories.map { it.toAppleProductCategoryEntity() })
        }
    }
}
