package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.AppleBoxItemsDao
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.source.AppleBoxItemsDataSource
import com.udtt.applegamsung.util.AsyncExecutor
import kotlinx.coroutines.CoroutineScope

class AppleBoxItemsLocalDataSource(
    private val asyncExecutor: AsyncExecutor,
    private val appleBoxItemsDao: AppleBoxItemsDao,
    private val mainCoroutineScope: CoroutineScope,
    private val ioCoroutineScope: CoroutineScope,
) : AppleBoxItemsDataSource {

    override fun getAppleBoxItems(callback: (appleBoxItems: List<AppleBoxItem>) -> Unit) {
        asyncExecutor.ioThread.execute {
            val appleBoxItems = appleBoxItemsDao.getAppleBoxItems()
            asyncExecutor.mainThread.execute { callback(appleBoxItems) }
        }
    }

    override fun saveAppleBoxItems(appleBoxItems: List<AppleBoxItem>) {
        asyncExecutor.ioThread.execute {
            appleBoxItemsDao.insertAppleBoxItems(appleBoxItems)
        }
    }

    override fun removeAppleBoxItem(itemToRemove: AppleBoxItem) {
        asyncExecutor.ioThread.execute {
            appleBoxItemsDao.deleteAppleBoxItem(itemToRemove)
        }
    }

    override fun removeAppleBoxItems(itemsToRemove: List<AppleBoxItem>) {
        asyncExecutor.ioThread.execute {
            appleBoxItemsDao.deleteAppleBoxItems(itemsToRemove)
        }
    }

    override fun removeAllAppleBoxItems() {
        asyncExecutor.ioThread.execute {
            appleBoxItemsDao.deleteAllAppleBoxItems()
        }
    }
}
