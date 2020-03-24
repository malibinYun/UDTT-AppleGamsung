package com.udtt.applegamsung.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_PRODUCTS

/**
 * Created By Yun Hyeok
 * on 3월 24, 2020
 */

class MainViewModel : ViewModel() {

    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int>
        get() = _currentPage

    private val _selectedCategoryId = MutableLiveData<String>()
    val selectedCategoryId: LiveData<String>
        get() = _selectedCategoryId

    fun selectCategory(categoryId: String) {
        _selectedCategoryId.value = categoryId
        movePageTo(FRAGMENT_PRODUCTS)
    }

    fun movePageTo(pageNum: Int) {
        _currentPage.value = pageNum
    }

}