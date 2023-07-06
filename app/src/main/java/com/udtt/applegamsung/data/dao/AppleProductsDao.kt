package com.udtt.applegamsung.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.udtt.applegamsung.data.entity.AppleProductEntity

@Dao
interface AppleProductsDao {

    @Query("SELECT * FROM AppleProductEntity WHERE appleProductCategoryId = :categoryId ORDER BY name")
    suspend fun getProductsByCategoryId(categoryId: String): List<AppleProductEntity>

    @Insert
    suspend fun insertProducts(products: List<AppleProductEntity>)
}
