package com.udtt.applegamsung.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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

}