package com.udtt.applegamsung.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.data.repository.CategoriesRepository
import com.udtt.applegamsung.ui.categories.CategoriesViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val categoriesRepository: CategoriesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {

            CategoriesViewModel::class.java ->
                modelClass.getConstructor(CATEGORIES_REPO).newInstance(categoriesRepository)

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
    }

    companion object {
        private val CATEGORIES_REPO = CategoriesRepository::class.java
    }
}