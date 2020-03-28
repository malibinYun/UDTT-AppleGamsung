package com.udtt.applegamsung.ui.applebox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.repository.AppleBoxItemsRepository
import com.udtt.applegamsung.util.BaseViewModel

class AppleBoxViewModel(
    private val appleBoxItemsRepository: AppleBoxItemsRepository
) : BaseViewModel() {

    private val _appleBoxItems = MutableLiveData<List<AppleBoxItem>>()
    val appleBoxItems: LiveData<List<AppleBoxItem>>
        get() = _appleBoxItems

    init {
        loadAppleBoxItems()
    }

    private fun loadAppleBoxItems() {
        _isLoading.value = true
        appleBoxItemsRepository.getAppleBoxItems {
            _appleBoxItems.value = it
            _isLoading.value = false
        }
    }
}