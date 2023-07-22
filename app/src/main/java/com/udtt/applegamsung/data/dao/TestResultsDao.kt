package com.udtt.applegamsung.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.udtt.applegamsung.data.entity.TestResultEntity


@Dao
interface TestResultsDao {

    @Query("SELECT * FROM TestResultEntity")
    suspend fun getTestResults(): List<TestResultEntity>

    @Insert
    suspend fun insertTestResult(testResult: TestResultEntity)

    @Query("DELETE FROM TestResultEntity")
    suspend fun deleteAllTestResults()
}
