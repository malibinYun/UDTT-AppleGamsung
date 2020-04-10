package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult
import com.udtt.applegamsung.data.source.TestResultsDataSource
import com.udtt.applegamsung.util.log

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
        //testResultsRemoteDataSource.saveTestResult(testResult)
    }

    override fun getApplePowers(callback: (applePowers: List<ApplePower>) -> Unit) {
        log("getApplePowers Called")
        testResultsLocalDataSource.getApplePowers { applePowers ->
            if (applePowers.isEmpty()) getApplePowersFromRemoteSource(callback)
            else callback(applePowers)
        }
    }

    private fun getApplePowersFromRemoteSource(callback: (applePowers: List<ApplePower>) -> Unit) {
        log("getApplePowersFromRemoteSource Called")
        testResultsRemoteDataSource.getApplePowers {
            saveApplePowers(it)
            callback(it)
        }
    }

    override fun getApplePower(totalScore: Int, callback: (applePower: ApplePower?) -> Unit) {
        log("getApplePower Called")
        testResultsLocalDataSource.getApplePower(totalScore) {
            if (it == null) loadApplePowersAndGetPower(totalScore, callback)
            else callback(it)
        }
    }

    private fun loadApplePowersAndGetPower(
        totalScore: Int,
        callback: (applePower: ApplePower?) -> Unit
    ) {
        log("loadApplePowersAndGetPower Called")
        getApplePowers { getApplePower(totalScore, callback) }
    }

    override fun saveApplePowers(applePowers: List<ApplePower>) {
        log("saveApplePowers Called")
        testResultsLocalDataSource.saveApplePowers(applePowers)
    }

    override fun removeAllTestResults() {
        testResultsLocalDataSource.removeAllTestResults()
    }
}