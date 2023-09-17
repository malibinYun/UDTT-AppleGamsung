package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult
import com.udtt.applegamsung.data.source.TestResultsDataSource
import com.udtt.applegamsung.domain.repository.TestResultsRepository
import com.udtt.applegamsung.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created By Yun Hyeok
 * on 3월 22, 2020
 */

class DefaultTestResultsRepository(
    private val testResultsRemoteDataSource: TestResultsDataSource,
    private val testResultsLocalDataSource: TestResultsDataSource
) : TestResultsRepository {

    override fun getTestResults(callback: (testResults: List<TestResult>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            testResultsLocalDataSource.getTestResults()
                .onSuccess(callback)
                .onFailure { throw it }
        }
    }

    override fun saveTestResult(testResult: TestResult) {
        CoroutineScope(Dispatchers.IO).launch {
            testResultsLocalDataSource.saveTestResult(testResult)
                .onFailure { throw it }
        }
        CoroutineScope(Dispatchers.IO).launch {
            testResultsRemoteDataSource.saveTestResult(testResult)
                .onFailure { throw it }
        }
    }

    override fun getApplePowers(callback: (applePowers: List<ApplePower>) -> Unit) {
        log("getApplePowers Called")
        CoroutineScope(Dispatchers.Main).launch {
            testResultsLocalDataSource.getApplePowers()
                .onSuccess {
                    if (it.isEmpty()) getApplePowersFromRemoteSource(callback)
                    else callback(it)
                }
                .onFailure { throw it }
        }
    }

    private fun getApplePowersFromRemoteSource(callback: (applePowers: List<ApplePower>) -> Unit) {
        log("getApplePowersFromRemoteSource Called")
        CoroutineScope(Dispatchers.Main).launch {
            testResultsRemoteDataSource.getApplePowers()
                .onSuccess {
                    saveApplePowers(it)
                    callback(it)
                }
                .onFailure { throw it }
        }
    }

    override fun getApplePower(totalScore: Int, callback: (applePower: ApplePower?) -> Unit) {
        log("getApplePower Called")
        CoroutineScope(Dispatchers.Main).launch {
            testResultsLocalDataSource.getApplePower(totalScore)
                .onSuccess {
                    if (it == null) loadApplePowersAndGetPower(totalScore, callback)
                    else callback(it)
                }
                .onFailure { throw it }
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
        CoroutineScope(Dispatchers.IO).launch {
            testResultsLocalDataSource.saveApplePowers(applePowers)
                .onFailure { throw it }
        }
    }

    override fun removeAllTestResults() {
        CoroutineScope(Dispatchers.IO).launch {
            testResultsLocalDataSource.removeAllTestResults()
                .onFailure { throw it }
        }
    }
}
