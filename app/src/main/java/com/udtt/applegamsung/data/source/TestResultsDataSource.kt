package com.udtt.applegamsung.data.source

import com.udtt.applegamsung.domain.model.testresult.applepower.ApplePower
import com.udtt.applegamsung.domain.model.testresult.TestResult

/**
 * Created By Yun Hyeok
 * on 3월 21, 2020
 */

interface TestResultsDataSource {

    suspend fun getTestResults(): Result<List<TestResult>>

    suspend fun saveTestResult(testResult: TestResult): Result<Unit>

    suspend fun removeAllTestResults(): Result<Unit>

    suspend fun getApplePowers(): Result<List<ApplePower>>

    suspend fun getApplePower(totalScore: Int): Result<ApplePower?>

    suspend fun saveApplePowers(applePowers: List<ApplePower>): Result<Unit>
}
