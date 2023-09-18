package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.source.CategoriesDataSource
import com.udtt.applegamsung.domain.repository.CategoriesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

class DefaultCategoriesRepository(
    private val categoriesRemoteDataSource: CategoriesDataSource,
    private val categoriesLocalDataSource: CategoriesDataSource
) : CategoriesRepository {

    override fun getCategories(callback: (categories: List<Category>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            categoriesLocalDataSource.getCategories()
                .onSuccess {
                    if (it.isEmpty()) getCategoriesFromRemoteDataSource(callback)
                    else callback(it)
                }
                .onFailure { getCategoriesFromRemoteDataSource(callback) }
        }
    }

    override fun saveCategories(categories: List<Category>) {
        CoroutineScope(Dispatchers.IO).launch {
            categoriesLocalDataSource.saveCategories(categories)
        }
    }

    private fun getCategoriesFromRemoteDataSource(callback: (categories: List<Category>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            categoriesRemoteDataSource.getCategories()
                .onSuccess {
                    callback(it)
                    saveCategories(it)
                }
                .onFailure { throw it }
        }
    }
}
