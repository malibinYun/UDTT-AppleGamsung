package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.CategoriesDao
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.source.CategoriesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

class CategoriesLocalDataSource(
    private val categoriesDao: CategoriesDao,
    private val mainCoroutineScope: CoroutineScope,
    private val ioCoroutineScope: CoroutineScope,
) : CategoriesDataSource {

    override fun getCategories(callback: (categories: List<Category>) -> Unit) {
        ioCoroutineScope.launch {
            val categories = categoriesDao.getCategories()
            mainCoroutineScope.launch { callback(categories) }
        }
    }

    override fun saveCategories(categories: List<Category>) {
        ioCoroutineScope.launch {
            categoriesDao.insertCategories(categories)
        }
    }
}
