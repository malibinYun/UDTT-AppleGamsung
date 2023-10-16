package com.udtt.applegamsung.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.udtt.applegamsung.data.entity.TestResultProductEntity

@Dao
interface TestResultProductsDao {

    @Insert
    suspend fun insertTestResultProducts(products: List<TestResultProductEntity>)

    @Query("SELECT * FROM TestResultProductEntity")
    suspend fun getAllTestResultProducts(): List<TestResultProductEntity>
}
