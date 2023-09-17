package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.source.CategoriesDataSource
import com.udtt.applegamsung.domain.repository.CategoriesRepository
import com.udtt.applegamsung.util.log

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

class DefaultCategoriesRepository(
    private val categoriesRemoteDataSource: CategoriesDataSource,
    private val categoriesLocalDataSource: CategoriesDataSource
) : CategoriesRepository {

    override fun getCategories(callback: (categories: List<Category>) -> Unit) {
        categoriesLocalDataSource.getCategories {
            log("categoriesLocalDataSource.getCategories Called")
            if (it.isEmpty()) getCategoriesFromRemoteDataSource(callback)
            else callback(it)
        }
    }

    override fun saveCategories(categories: List<Category>) {
        categoriesLocalDataSource.saveCategories(categories)
    }

    private fun getCategoriesFromRemoteDataSource(callback: (categories: List<Category>) -> Unit) {
        categoriesRemoteDataSource.getCategories {
            log("categoriesRemoteDataSource.getCategories Called")
            callback(it)
            saveCategories(it)
        }
    }
}
