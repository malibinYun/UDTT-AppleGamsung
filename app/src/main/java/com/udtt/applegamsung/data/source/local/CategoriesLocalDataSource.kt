package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.CategoriesDao
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.source.CategoriesDataSource
import com.udtt.applegamsung.util.AsyncExecutor
import kotlinx.coroutines.CoroutineScope

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

class CategoriesLocalDataSource(
    private val asyncExecutor: AsyncExecutor,
    private val categoriesDao: CategoriesDao,
    private val mainCoroutineScope: CoroutineScope,
    private val ioCoroutineScope: CoroutineScope,
) : CategoriesDataSource {

    override fun getCategories(callback: (categories: List<Category>) -> Unit) {
        asyncExecutor.ioThread.execute {
            val categories = categoriesDao.getCategories()
            asyncExecutor.mainThread.execute { callback(categories) }
        }
    }

    override fun saveCategories(categories: List<Category>) {
        asyncExecutor.ioThread.execute {
            categoriesDao.insertCategories(categories)
        }
    }
}
