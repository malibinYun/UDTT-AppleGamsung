package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.AppleProductsDao
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.local.mapper.toAppleBoxItem
import com.udtt.applegamsung.data.local.mapper.toAppleProductEntity
import com.udtt.applegamsung.data.source.AppleBoxItemsDataSource

class LocalAppleBoxItemsDataSource(
    private val appleProductsDao: AppleProductsDao,
) : AppleBoxItemsDataSource {

    override suspend fun getAppleBoxItems(): Result<List<AppleBoxItem>> {
        return runCatching { appleProductsDao.getInBoxAppleProducts() }
            .map { appleProductEntities ->
                appleProductEntities.map { it.toAppleBoxItem() }
            }
    }

    override suspend fun saveAppleBoxItems(appleBoxItems: List<AppleBoxItem>): Result<Unit> {
        return runCatching {
            appleProductsDao.updateAppleProduct(appleBoxItems.map { it.toAppleProductEntity(isInBox = true) })
        }
    }

    override suspend fun removeAppleBoxItem(itemToRemove: AppleBoxItem): Result<Unit> {
        return runCatching {
            appleProductsDao.updateAppleProduct(itemToRemove.toAppleProductEntity(isInBox = false))
        }
    }

    override suspend fun removeAppleBoxItems(itemsToRemove: List<AppleBoxItem>): Result<Unit> {
        return runCatching {
            appleProductsDao.updateAppleProduct(itemsToRemove.map { it.toAppleProductEntity(isInBox = false) })
        }
    }

    override suspend fun removeAllAppleBoxItems(): Result<Unit> {
        return runCatching {
            appleProductsDao.updateAllProductUnSelected()
        }
    }
}
