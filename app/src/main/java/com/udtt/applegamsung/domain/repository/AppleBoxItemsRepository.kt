package com.udtt.applegamsung.domain.repository

import com.udtt.applegamsung.data.entity.AppleBoxItem

interface AppleBoxItemsRepository {

    suspend fun getAppleBoxItems(): Result<List<AppleBoxItem>>

    suspend fun saveAppleBoxItems(appleBoxItems: List<AppleBoxItem>): Result<Unit>

    suspend fun removeAppleBoxItem(itemToRemove: AppleBoxItem): Result<Unit>

    suspend fun removeAppleBoxItems(itemsToRemove: List<AppleBoxItem>): Result<Unit>

    suspend fun removeAllAppleBoxItems(): Result<Unit>
}
