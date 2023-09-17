package com.udtt.applegamsung.domain.repository

import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult

/**
 * Created By Yun Hyeok
 * on 3월 21, 2020
 */

interface TestResultsRepository {

    fun getTestResults(callback: (testResults: List<TestResult>) -> Unit)

    fun saveTestResult(testResult: TestResult)

    fun removeAllTestResults()

    fun getApplePowers(callback: (applePowers: List<ApplePower>) -> Unit)

    fun getApplePower(totalScore: Int, callback: (applePower: ApplePower?) -> Unit)

    fun saveApplePowers(applePowers: List<ApplePower>)
}
