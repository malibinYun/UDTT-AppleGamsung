package com.udtt.applegamsung.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.udtt.applegamsung.data.entity.Product

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

@Dao
interface ProductsDao {

    @Query("SELECT * FROM product WHERE categoryId = :categoryId ORDER BY name")
    suspend fun getProductsByCategoryId(categoryId: String): List<Product>

    @Insert
    suspend fun insertProducts(products: List<Product>)
}
