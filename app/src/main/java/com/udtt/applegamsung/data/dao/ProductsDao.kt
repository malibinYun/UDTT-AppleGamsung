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

    @Query("SELECT * FROM product")
    fun getProducts(): List<Product>

    @Insert
    fun insertProducts(products: List<Product>)

}