package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.TestResultsDao
import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult
import com.udtt.applegamsung.data.source.TestResultsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created By Yun Hyeok
 * on 3월 21, 2020
 */

class TestResultsLocalDataSource(
    private val testResultsDao: TestResultsDao,
    private val mainCoroutineScope: CoroutineScope,
    private val ioCoroutineScope: CoroutineScope,
) : TestResultsDataSource {

    override fun getTestResults(callback: (testResults: List<TestResult>) -> Unit) {
        ioCoroutineScope.launch {
            val testResults = testResultsDao.getTestResults()
            mainCoroutineScope.launch { callback(testResults) }
        }
    }

    override fun saveTestResult(testResult: TestResult) {
        ioCoroutineScope.launch {
            testResultsDao.insertTestResult(testResult)
        }
    }

    override fun getApplePowers(callback: (applePowers: List<ApplePower>) -> Unit) {
        ioCoroutineScope.launch {
            val applePowers = testResultsDao.getApplePowers()
            mainCoroutineScope.launch { callback(applePowers) }
        }
    }

    override fun getApplePower(totalScore: Int, callback: (applePower: ApplePower?) -> Unit) {
        ioCoroutineScope.launch {
            val applePower = testResultsDao.getApplePower(totalScore)
            mainCoroutineScope.launch { callback(applePower) }
        }
    }

    override fun saveApplePowers(applePowers: List<ApplePower>) {
        ioCoroutineScope.launch {
            testResultsDao.insertApplePower(applePowers)
        }
    }

    override fun removeAllTestResults() {
        ioCoroutineScope.launch {
            testResultsDao.deleteAllTestResults()
        }
    }
}
