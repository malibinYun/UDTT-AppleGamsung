package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.source.AppleBoxItemsDataSource
import com.udtt.applegamsung.domain.repository.AppleBoxItemsRepository

class DefaultAppleBoxItemsRepository(
    private val localAppleBoxItemsDataSource: AppleBoxItemsDataSource,
) : AppleBoxItemsRepository {

    override suspend fun getAppleBoxItems(): Result<List<AppleBoxItem>> {
        return localAppleBoxItemsDataSource.getAppleBoxItems()
    }

    override suspend fun saveAppleBoxItems(appleBoxItems: List<AppleBoxItem>): Result<Unit> {
        return localAppleBoxItemsDataSource.saveAppleBoxItems(appleBoxItems)
    }

    override suspend fun removeAppleBoxItem(itemToRemove: AppleBoxItem): Result<Unit> {
        return localAppleBoxItemsDataSource.removeAppleBoxItem(itemToRemove)
    }

    override suspend fun removeAppleBoxItems(itemsToRemove: List<AppleBoxItem>): Result<Unit> {
        return localAppleBoxItemsDataSource.removeAppleBoxItems(itemsToRemove)
    }

    override suspend fun removeAllAppleBoxItems(): Result<Unit> {
        return localAppleBoxItemsDataSource.removeAllAppleBoxItems()
    }
}
