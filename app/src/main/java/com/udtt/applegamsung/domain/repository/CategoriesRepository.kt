package com.udtt.applegamsung.domain.repository

import com.udtt.applegamsung.data.entity.Category

interface CategoriesRepository {

    fun getCategories(callback: (categories: List<Category>) -> Unit)

    fun saveCategories(categories: List<Category>)
}
