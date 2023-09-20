package com.udtt.applegamsung.ui.main.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.domain.repository.CategoriesRepository
import com.udtt.applegamsung.util.BaseViewModel
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val categoriesRepository: CategoriesRepository,
) : BaseViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _isLoading.value = true

            categoriesRepository.getCategories()
                .onSuccess { _categories.value = it.sortedBy { category -> category.index } }

            _isLoading.value = false
        }
    }
}
