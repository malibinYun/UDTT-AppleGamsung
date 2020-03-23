package com.udtt.applegamsung.ui.main.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.repository.CategoriesRepository
import com.udtt.applegamsung.util.BaseViewModel

class CategoriesViewModel(
    private val categoriesRepository: CategoriesRepository
) : BaseViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _isLoading.value = true
        categoriesRepository.getCategories {
            _categories.value = it.sortedBy { it.index }
            _isLoading.value = false
        }
    }
}