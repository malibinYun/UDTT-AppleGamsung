package com.udtt.applegamsung.ui.applebox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.repository.UserIdentifyRepository
import com.udtt.applegamsung.domain.repository.AppleBoxItemsRepository
import com.udtt.applegamsung.util.BaseViewModel

class AppleBoxViewModel(
    userIdentifyRepository: UserIdentifyRepository,
    private val appleBoxItemsRepository: AppleBoxItemsRepository
) : BaseViewModel() {

    val userNickName = userIdentifyRepository.getNickname()

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

    fun deleteAppleBoxItem(item: AppleBoxItem) {
        val currentAppleBoxItems = getCurrentAppleBoxItems()
        currentAppleBoxItems.remove(item)
        _appleBoxItems.value = currentAppleBoxItems
        appleBoxItemsRepository.removeAppleBoxItem(item)
    }

    private fun getCurrentAppleBoxItems(): MutableList<AppleBoxItem> {
        return _appleBoxItems.value?.toMutableList()
            ?: throw IllegalStateException("appleBoxItems는 null일 수 없음")
    }
}
