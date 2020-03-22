package com.udtt.applegamsung.data.source

import com.udtt.applegamsung.data.entity.TestResult

/**
 * Created By Yun Hyeok
 * on 3월 21, 2020
 */

interface TestResultsDataSource {

    fun getTestResults(callback: (testResults: List<TestResult>) -> Unit)

    fun saveTestResult(testResult: TestResult)

}