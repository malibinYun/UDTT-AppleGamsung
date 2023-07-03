package com.udtt.applegamsung.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.udtt.applegamsung.data.entity.AppleBoxItem

@Dao
interface AppleBoxItemsDao {

    @Query("SELECT * FROM appleboxitem ORDER BY categoryIndex, name")
    suspend fun getAppleBoxItems(): List<AppleBoxItem>

    @Insert
    suspend fun insertAppleBoxItems(appleBoxItems: List<AppleBoxItem>)

    @Delete
    suspend fun deleteAppleBoxItem(appleBoxItem: AppleBoxItem)

    @Delete
    suspend fun deleteAppleBoxItems(appleBoxItems: List<AppleBoxItem>)

    @Query("DELETE FROM appleboxitem")
    suspend fun deleteAllAppleBoxItems()
}
