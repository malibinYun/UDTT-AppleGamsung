package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.ApplePowersDao
import com.udtt.applegamsung.data.dao.TestResultsDao
import com.udtt.applegamsung.domain.model.testresult.applepower.ApplePower
import com.udtt.applegamsung.domain.model.testresult.TestResult
import com.udtt.applegamsung.data.local.mapper.toApplePower
import com.udtt.applegamsung.data.local.mapper.toApplePowerEntity
import com.udtt.applegamsung.data.local.mapper.toTestResult
import com.udtt.applegamsung.data.local.mapper.toTestResultEntity
import com.udtt.applegamsung.data.source.TestResultsDataSource

/**
 * Created By Yun Hyeok
 * on 3월 21, 2020
 */

class LocalTestResultsDataSource(
    private val testResultsDao: TestResultsDao,
    private val applePowersDao: ApplePowersDao,
) : TestResultsDataSource {

    override suspend fun getTestResults(): Result<List<TestResult>> {
        return runCatching { testResultsDao.getTestResults() }
            .map { testResultEntities -> testResultEntities.map { it.toTestResult() } }
    }

    override suspend fun saveTestResult(testResult: TestResult): Result<Unit> {
        return runCatching {
            testResultsDao.insertTestResult(testResult.toTestResultEntity())
        }
    }

    override suspend fun getApplePowers(): Result<List<ApplePower>> {
        return runCatching { applePowersDao.getApplePowers() }
            .map { applePowerEntities -> applePowerEntities.map { it.toApplePower() } }
    }

    override suspend fun getApplePower(totalScore: Int): Result<ApplePower?> {
        return runCatching { applePowersDao.getApplePowers() }
            .map { applePowerEntities ->
                applePowerEntities.find { totalScore in it.minScore..it.maxScore }
                    ?.toApplePower()
            }
    }

    override suspend fun saveApplePowers(applePowers: List<ApplePower>): Result<Unit> {
        return runCatching {
            applePowersDao.insertApplePower(applePowers.map { it.toApplePowerEntity() })
        }
    }

    override suspend fun removeAllTestResults(): Result<Unit> {
        return runCatching {
            testResultsDao.deleteAllTestResults()
        }
    }
}
