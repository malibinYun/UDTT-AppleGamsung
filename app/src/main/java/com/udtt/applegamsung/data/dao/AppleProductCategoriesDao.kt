package com.udtt.applegamsung.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udtt.applegamsung.data.entity.AppleProductCategoryEntity

@Dao
interface AppleProductCategoriesDao {

    @Query("SELECT * FROM AppleProductCategoryEntity")
    suspend fun getCategories(): List<AppleProductCategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<AppleProductCategoryEntity>)
}
