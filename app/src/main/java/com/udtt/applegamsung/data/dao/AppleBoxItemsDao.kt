package com.udtt.applegamsung.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.udtt.applegamsung.data.entity.AppleBoxItem

@Dao
interface AppleBoxItemsDao {

    @Query("SELECT * FROM appleboxitem")
    fun getAppleBoxItems(): List<AppleBoxItem>

    @Insert
    fun insertAppleBoxItems(appleBoxItems: List<AppleBoxItem>)

    @Delete
    fun deleteAppleBoxItem(appleBoxItem: AppleBoxItem)

    @Delete
    fun deleteAppleBoxItems(appleBoxItems: List<AppleBoxItem>)

}