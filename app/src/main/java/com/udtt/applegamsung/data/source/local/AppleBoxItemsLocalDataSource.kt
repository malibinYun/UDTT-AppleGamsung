package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.AppleProductsDao
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.local.mapper.toAppleBoxItem
import com.udtt.applegamsung.data.local.mapper.toAppleProductEntity
import com.udtt.applegamsung.data.source.AppleBoxItemsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AppleBoxItemsLocalDataSource(
    private val appleProductsDao: AppleProductsDao,
    private val mainCoroutineScope: CoroutineScope,
    private val ioCoroutineScope: CoroutineScope,
) : AppleBoxItemsDataSource {

    override fun getAppleBoxItems(callback: (appleBoxItems: List<AppleBoxItem>) -> Unit) {
        ioCoroutineScope.launch {
            val appleBoxItems = appleProductsDao.getInBoxAppleProducts()
                .map { it.toAppleBoxItem() }
            mainCoroutineScope.launch { callback(appleBoxItems) }
        }
    }

    override fun saveAppleBoxItems(appleBoxItems: List<AppleBoxItem>) {
        ioCoroutineScope.launch {
            appleProductsDao.updateAppleProduct(appleBoxItems.map { it.toAppleProductEntity(isInBox = true) })
        }
    }

    override fun removeAppleBoxItem(itemToRemove: AppleBoxItem) {
        ioCoroutineScope.launch {
            appleProductsDao.updateAppleProduct(itemToRemove.toAppleProductEntity(isInBox = false))
        }
    }

    override fun removeAppleBoxItems(itemsToRemove: List<AppleBoxItem>) {
        ioCoroutineScope.launch {
            appleProductsDao.updateAppleProduct(itemsToRemove.map { it.toAppleProductEntity(isInBox = false) })
        }
    }

    override fun removeAllAppleBoxItems() {
        ioCoroutineScope.launch {
            appleProductsDao.updateAllProductUnSelected()
        }
    }
}
