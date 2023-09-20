package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult
import com.udtt.applegamsung.data.source.TestResultsDataSource
import com.udtt.applegamsung.domain.repository.TestResultsRepository
import com.udtt.applegamsung.util.getOrEmpty

/**
 * Created By Yun Hyeok
 * on 3월 22, 2020
 */

class DefaultTestResultsRepository(
    private val remoteTestResultsDataSource: TestResultsDataSource,
    private val localTestResultsDataSource: TestResultsDataSource,
) : TestResultsRepository {

    override suspend fun getTestResults(): Result<List<TestResult>> {
        return localTestResultsDataSource.getTestResults()
    }

    override suspend fun saveTestResult(testResult: TestResult): Result<Unit> {
        remoteTestResultsDataSource.saveTestResult(testResult)
            .onFailure { return Result.failure(it) }

        localTestResultsDataSource.saveTestResult(testResult)
            .onFailure { return Result.failure(it) }

        return Result.success(Unit)
    }

    override suspend fun getApplePowers(): Result<List<ApplePower>> {
        val localApplePowers = localTestResultsDataSource.getApplePowers()
        if (localApplePowers.getOrEmpty().isNotEmpty()) {
            return localApplePowers
        }
        return remoteTestResultsDataSource.getApplePowers()
            .onSuccess { saveApplePowers(it) }
    }

    override suspend fun getApplePower(totalScore: Int): Result<ApplePower?> {
        val localApplePower = localTestResultsDataSource.getApplePower(totalScore)
        if (localApplePower.getOrNull() != null) {
            return localApplePower
        }
        getApplePowers()
        return getApplePower(totalScore)
    }

    override suspend fun saveApplePowers(applePowers: List<ApplePower>): Result<Unit> {
        return localTestResultsDataSource.saveApplePowers(applePowers)
    }

    override suspend fun removeAllTestResults(): Result<Unit> {
        return localTestResultsDataSource.removeAllTestResults()
    }
}
