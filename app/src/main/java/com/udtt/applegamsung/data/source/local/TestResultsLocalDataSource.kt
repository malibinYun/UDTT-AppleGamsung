package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.TestResultsDao
import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult
import com.udtt.applegamsung.data.source.TestResultsDataSource
import com.udtt.applegamsung.util.AsyncExecutor

/**
 * Created By Yun Hyeok
 * on 3월 21, 2020
 */

class TestResultsLocalDataSource(
    private val asyncExecutor: AsyncExecutor,
    private val testResultsDao: TestResultsDao
) : TestResultsDataSource {

    override fun getTestResults(callback: (testResults: List<TestResult>) -> Unit) {
        asyncExecutor.ioThread.execute {
            val testResults = testResultsDao.getTestResults()
            asyncExecutor.mainThread.execute { callback(testResults) }
        }
    }

    override fun saveTestResult(testResult: TestResult) {
        asyncExecutor.ioThread.execute {
            testResultsDao.insertTestResult(testResult)
        }
    }

    override fun getApplePowers(callback: (applePowers: List<ApplePower>) -> Unit) {
        asyncExecutor.ioThread.execute {
            val applePowers = testResultsDao.getApplePowers()
            asyncExecutor.mainThread.execute { callback(applePowers) }
        }
    }

    override fun getApplePower(totalScore: Int, callback: (applePower: ApplePower?) -> Unit) {
        asyncExecutor.ioThread.execute {
            val applePower = testResultsDao.getApplePower(totalScore)
            asyncExecutor.mainThread.execute { callback(applePower) }
        }
    }

    override fun saveApplePowers(applePowers: List<ApplePower>) {
        asyncExecutor.ioThread.execute {
            testResultsDao.insertApplePower(applePowers)
        }
    }

    override fun removeAllTestResults() {
        asyncExecutor.ioThread.execute {
            testResultsDao.deleteAllTestResults()
        }
    }
}