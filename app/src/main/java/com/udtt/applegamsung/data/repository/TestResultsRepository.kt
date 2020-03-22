package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.TestResult
import com.udtt.applegamsung.data.source.TestResultsDataSource

/**
 * Created By Yun Hyeok
 * on 3월 22, 2020
 */

class TestResultsRepository(
    private val testResultsRemoteDataSource: TestResultsDataSource,
    private val testResultsLocalDataSource: TestResultsDataSource
) : TestResultsDataSource {

    override fun getTestResults(callback: (testResults: List<TestResult>) -> Unit) {
        testResultsLocalDataSource.getTestResults { callback(it) }
    }

    override fun saveTestResult(testResult: TestResult) {
        testResultsLocalDataSource.saveTestResult(testResult)
        testResultsRemoteDataSource.saveTestResult(testResult)
    }
}