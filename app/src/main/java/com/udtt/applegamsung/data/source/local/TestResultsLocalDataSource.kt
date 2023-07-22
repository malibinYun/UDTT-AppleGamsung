package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.ApplePowersDao
import com.udtt.applegamsung.data.dao.TestResultsDao
import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult
import com.udtt.applegamsung.data.local.mapper.toApplePower
import com.udtt.applegamsung.data.local.mapper.toApplePowerEntity
import com.udtt.applegamsung.data.local.mapper.toTestResult
import com.udtt.applegamsung.data.local.mapper.toTestResultEntity
import com.udtt.applegamsung.data.source.TestResultsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created By Yun Hyeok
 * on 3월 21, 2020
 */

class TestResultsLocalDataSource(
    private val testResultsDao: TestResultsDao,
    private val applePowersDao: ApplePowersDao,
    private val mainCoroutineScope: CoroutineScope,
    private val ioCoroutineScope: CoroutineScope,
) : TestResultsDataSource {

    override fun getTestResults(callback: (testResults: List<TestResult>) -> Unit) {
        ioCoroutineScope.launch {
            val testResults = testResultsDao.getTestResults()
                .map { it.toTestResult() }
            mainCoroutineScope.launch { callback(testResults) }
        }
    }

    override fun saveTestResult(testResult: TestResult) {
        ioCoroutineScope.launch {
            testResultsDao.insertTestResult(testResult.toTestResultEntity())
        }
    }

    override fun getApplePowers(callback: (applePowers: List<ApplePower>) -> Unit) {
        ioCoroutineScope.launch {
            val applePowers = applePowersDao.getApplePowers()
                .map { it.toApplePower() }
            mainCoroutineScope.launch { callback(applePowers) }
        }
    }

    override fun getApplePower(totalScore: Int, callback: (applePower: ApplePower?) -> Unit) {
        ioCoroutineScope.launch {
            val applePower = applePowersDao.getApplePowers()
                .first { totalScore in it.minScore..it.maxScore }
                .toApplePower()

            mainCoroutineScope.launch { callback(applePower) }
        }
    }

    override fun saveApplePowers(applePowers: List<ApplePower>) {
        ioCoroutineScope.launch {
            applePowersDao.insertApplePower(applePowers.map { it.toApplePowerEntity() })
        }
    }

    override fun removeAllTestResults() {
        ioCoroutineScope.launch {
            testResultsDao.deleteAllTestResults()
        }
    }
}
