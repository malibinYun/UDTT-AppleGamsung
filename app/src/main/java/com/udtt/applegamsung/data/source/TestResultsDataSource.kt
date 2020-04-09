package com.udtt.applegamsung.data.source

import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult

/**
 * Created By Yun Hyeok
 * on 3월 21, 2020
 */

interface TestResultsDataSource {

    fun getTestResults(callback: (testResults: List<TestResult>) -> Unit)

    fun saveTestResult(testResult: TestResult)

    fun removeAllTestResults()

    fun getApplePowers(callback: (applePowers: List<ApplePower>) -> Unit)

    fun getApplePower(totalScore: Int, callback: (applePower: ApplePower?) -> Unit)

    fun saveApplePowers(applePowers: List<ApplePower>)

}