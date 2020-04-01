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
    fun getTestResults(): List<TestResult>

    @Insert
    fun insertTestResult(testResult: TestResult)

    @Query("SELECT * FROM applepower WHERE maxPower >= :totalScore ORDER BY maxPower LIMIT(1)")
    fun getApplePower(totalScore: Int): ApplePower?

    @Insert
    fun insertApplePower(applePowers: List<ApplePower>)

}