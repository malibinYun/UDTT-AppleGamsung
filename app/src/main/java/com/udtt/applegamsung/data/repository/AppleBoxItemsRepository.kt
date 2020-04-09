package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.source.AppleBoxItemsDataSource

class AppleBoxItemsRepository(
    private val appleBoxItemsLocalDataSource: AppleBoxItemsDataSource
) : AppleBoxItemsDataSource {

    override fun getAppleBoxItems(callback: (appleBoxItems: List<AppleBoxItem>) -> Unit) {
        appleBoxItemsLocalDataSource.getAppleBoxItems { callback(it) }
    }

    override fun saveAppleBoxItems(appleBoxItems: List<AppleBoxItem>) {
        appleBoxItemsLocalDataSource.saveAppleBoxItems(appleBoxItems)
    }

    override fun removeAppleBoxItem(itemToRemove: AppleBoxItem) {
        appleBoxItemsLocalDataSource.removeAppleBoxItem(itemToRemove)
    }

    override fun removeAppleBoxItems(itemsToRemove: List<AppleBoxItem>) {
        appleBoxItemsLocalDataSource.removeAppleBoxItems(itemsToRemove)
    }

    override fun removeAllAppleBoxItems() {
        appleBoxItemsLocalDataSource.removeAllAppleBoxItems()
    }
}