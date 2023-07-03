package com.udtt.applegamsung.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

@Dao
interface TestResultsDao {

    @Query("SELECT * FROM testresult")
    suspend fun getTestResults(): List<TestResult>

    @Insert
    suspend fun insertTestResult(testResult: TestResult)

    @Query("DELETE FROM testresult")
    suspend fun deleteAllTestResults()

    @Query("SELECT * FROM applepower")
    suspend fun getApplePowers(): List<ApplePower>

    @Query("SELECT * FROM applepower WHERE maxPower >= :totalScore AND minPower <= :totalScore ORDER BY maxPower LIMIT(1)")
    suspend fun getApplePower(totalScore: Int): ApplePower?

    @Insert
    suspend fun insertApplePower(applePowers: List<ApplePower>)
}
