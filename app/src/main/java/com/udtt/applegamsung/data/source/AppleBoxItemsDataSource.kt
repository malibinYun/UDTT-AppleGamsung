package com.udtt.applegamsung.data.source

import com.udtt.applegamsung.data.entity.AppleBoxItem

interface AppleBoxItemsDataSource {

    suspend fun getAppleBoxItems(): Result<List<AppleBoxItem>>

    suspend fun saveAppleBoxItems(appleBoxItems: List<AppleBoxItem>): Result<Unit>

    suspend fun removeAppleBoxItem(itemToRemove: AppleBoxItem): Result<Unit>

    suspend fun removeAppleBoxItems(itemsToRemove: List<AppleBoxItem>): Result<Unit>

    suspend fun removeAllAppleBoxItems(): Result<Unit>
}
