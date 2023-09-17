package com.udtt.applegamsung.domain.repository

import com.udtt.applegamsung.data.entity.AppleBoxItem

interface AppleBoxItemsRepository {

    fun getAppleBoxItems(callback: (appleBoxItems: List<AppleBoxItem>) -> Unit)

    fun saveAppleBoxItems(appleBoxItems: List<AppleBoxItem>)

    fun removeAppleBoxItem(itemToRemove: AppleBoxItem)

    fun removeAppleBoxItems(itemsToRemove: List<AppleBoxItem>)

    fun removeAllAppleBoxItems()
}
