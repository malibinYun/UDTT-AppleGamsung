package com.udtt.applegamsung.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.udtt.applegamsung.data.entity.AppleProductEntity

@Dao
interface AppleProductsDao {

    @Insert
    suspend fun insertProducts(products: List<AppleProductEntity>)

    @Update
    suspend fun updateAppleProduct(product: AppleProductEntity)

    @Update
    suspend fun updateAppleProduct(products: List<AppleProductEntity>)

    @Query("SELECT * FROM AppleProductEntity WHERE appleProductCategoryId = :categoryId ORDER BY name")
    suspend fun getProductsByCategoryId(categoryId: String): List<AppleProductEntity>

    @Query("SELECT * FROM AppleProductEntity WHERE isInBox = 1")
    suspend fun getInBoxAppleProducts(): List<AppleProductEntity>

    @Query("UPDATE AppleProductEntity SET isInBox = 0, hasAppleCare = 0 WHERE isInBox = 1 OR hasAppleCare = 1")
    suspend fun updateAllProductUnSelected()
}
