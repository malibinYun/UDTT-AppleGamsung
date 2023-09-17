package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.source.AppleBoxItemsDataSource
import com.udtt.applegamsung.domain.repository.AppleBoxItemsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultAppleBoxItemsRepository(
    private val appleBoxItemsLocalDataSource: AppleBoxItemsDataSource
) : AppleBoxItemsRepository {

    override fun getAppleBoxItems(callback: (appleBoxItems: List<AppleBoxItem>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            appleBoxItemsLocalDataSource.getAppleBoxItems()
                .onSuccess(callback)
                .onFailure { throw it }
        }
    }

    override fun saveAppleBoxItems(appleBoxItems: List<AppleBoxItem>) {
        CoroutineScope(Dispatchers.IO).launch {
            appleBoxItemsLocalDataSource.saveAppleBoxItems(appleBoxItems)
        }
    }

    override fun removeAppleBoxItem(itemToRemove: AppleBoxItem) {
        CoroutineScope(Dispatchers.IO).launch {
            appleBoxItemsLocalDataSource.removeAppleBoxItem(itemToRemove)
        }
    }

    override fun removeAppleBoxItems(itemsToRemove: List<AppleBoxItem>) {
        CoroutineScope(Dispatchers.IO).launch {
            appleBoxItemsLocalDataSource.removeAppleBoxItems(itemsToRemove)
        }
    }

    override fun removeAllAppleBoxItems() {
        CoroutineScope(Dispatchers.IO).launch {
            appleBoxItemsLocalDataSource.removeAllAppleBoxItems()
        }
    }
}
