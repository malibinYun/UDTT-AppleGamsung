package com.udtt.applegamsung.data.source

import com.udtt.applegamsung.data.entity.Category

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

interface CategoriesDataSource {

    suspend fun getCategories(): Result<List<Category>>

    suspend fun saveCategories(categories: List<Category>): Result<Unit>
}
