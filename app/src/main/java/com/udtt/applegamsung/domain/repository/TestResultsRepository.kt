package com.udtt.applegamsung.domain.repository

import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult

/**
 * Created By Yun Hyeok
 * on 3월 21, 2020
 */

interface TestResultsRepository {

    suspend fun getTestResults(): Result<List<TestResult>>

    suspend fun saveTestResult(testResult: TestResult): Result<Unit>

    suspend fun removeAllTestResults(): Result<Unit>

    suspend fun getApplePowers(): Result<List<ApplePower>>

    suspend fun getApplePower(totalScore: Int): Result<ApplePower?>

    suspend fun saveApplePowers(applePowers: List<ApplePower>): Result<Unit>
}
