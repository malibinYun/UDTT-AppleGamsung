package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.AppleBoxItemsDao
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.source.AppleBoxItemsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AppleBoxItemsLocalDataSource(
    private val appleBoxItemsDao: AppleBoxItemsDao,
    private val mainCoroutineScope: CoroutineScope,
    private val ioCoroutineScope: CoroutineScope,
) : AppleBoxItemsDataSource {

    override fun getAppleBoxItems(callback: (appleBoxItems: List<AppleBoxItem>) -> Unit) {
        ioCoroutineScope.launch {
            val appleBoxItems = appleBoxItemsDao.getAppleBoxItems()
            mainCoroutineScope.launch { callback(appleBoxItems) }
        }
    }

    override fun saveAppleBoxItems(appleBoxItems: List<AppleBoxItem>) {
        ioCoroutineScope.launch {
            appleBoxItemsDao.insertAppleBoxItems(appleBoxItems)
        }
    }

    override fun removeAppleBoxItem(itemToRemove: AppleBoxItem) {
        ioCoroutineScope.launch {
            appleBoxItemsDao.deleteAppleBoxItem(itemToRemove)
        }
    }

    override fun removeAppleBoxItems(itemsToRemove: List<AppleBoxItem>) {
        ioCoroutineScope.launch {
            appleBoxItemsDao.deleteAppleBoxItems(itemsToRemove)
        }
    }

    override fun removeAllAppleBoxItems() {
        ioCoroutineScope.launch {
            appleBoxItemsDao.deleteAllAppleBoxItems()
        }
    }
}
