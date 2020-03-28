package com.udtt.applegamsung.data.source

import com.udtt.applegamsung.data.entity.AppleBoxItem

interface AppleBoxItemsDataSource {

    fun getAppleBoxItems(callback: (appleBoxItems: List<AppleBoxItem>) -> Unit)

    fun saveAppleBoxItems(appleBoxItems: List<AppleBoxItem>)

    fun removeAppleBoxItem(itemToRemove: AppleBoxItem)

    fun removeAppleBoxItems(itemsToRemove: List<AppleBoxItem>)

}