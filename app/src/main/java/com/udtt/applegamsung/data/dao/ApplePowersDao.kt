package com.udtt.applegamsung.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.udtt.applegamsung.data.entity.ApplePowerEntity

@Dao
interface ApplePowersDao {

    @Query("SELECT * FROM ApplePowerEntity")
    suspend fun getApplePowers(): List<ApplePowerEntity>

    @Insert
    suspend fun insertApplePower(applePowers: List<ApplePowerEntity>)
}
