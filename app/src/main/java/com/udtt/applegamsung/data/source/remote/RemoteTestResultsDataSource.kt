package com.udtt.applegamsung.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.domain.model.testresult.applepower.ApplePower
import com.udtt.applegamsung.domain.model.testresult.TestResult
import com.udtt.applegamsung.data.remote.firestore.addAwait
import com.udtt.applegamsung.data.remote.firestore.getDocumentSnapshots
import com.udtt.applegamsung.data.remote.mapper.toSaveResultParams
import com.udtt.applegamsung.data.source.TestResultsDataSource

/**
 * Created By Yun Hyeok
 * on 3월 22, 2020
 */

class RemoteTestResultsDataSource(
    private val firestore: FirebaseFirestore
) : TestResultsDataSource {

    override suspend fun getTestResults(): Result<List<TestResult>> {
        return Result.failure(
            UnsupportedOperationException("Do not call getTestResults() in remoteSource")
        )
    }

    override suspend fun saveTestResult(testResult: TestResult): Result<Unit> {
        return firestore.collection(PathTestResults)
            .addAwait(testResult.toSaveResultParams())
    }

    override suspend fun getApplePowers(): Result<List<ApplePower>> {
        return firestore.collection(PathApplePower)
            .getDocumentSnapshots()
            .map { documents ->
                documents.map {
                    ApplePower(
                        id = it.id,
                        name = it.getString("name").orEmpty(),
                        description = it.getString("description").orEmpty(),
                        minPower = it.getLong("minPower")?.toInt() ?: 0,
                        maxPower = it.getLong("maxPower")?.toInt() ?: 0,
                    )
                }
            }
    }

    override suspend fun getApplePower(totalScore: Int): Result<ApplePower?> {
        return Result.failure(
            UnsupportedOperationException("Do not call getApplePower() in remoteSource")
        )
    }

    override suspend fun saveApplePowers(applePowers: List<ApplePower>): Result<Unit> {
        return Result.failure(
            UnsupportedOperationException("Do not call saveApplePowers() in remoteSource")
        )
    }

    override suspend fun removeAllTestResults(): Result<Unit> {
        return Result.failure(
            UnsupportedOperationException("Do not call removeAllTestResults() in remoteSource")
        )
    }

    companion object {
        private const val PathTestResults = "testResults"
        private const val PathApplePower = "ApplePower"
    }
}
