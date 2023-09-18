package com.udtt.applegamsung.domain.repository

import com.udtt.applegamsung.data.entity.Category

interface CategoriesRepository {

    suspend fun getCategories(): Result<List<Category>>

    suspend fun saveCategories(categories: List<Category>): Result<Unit>
}
