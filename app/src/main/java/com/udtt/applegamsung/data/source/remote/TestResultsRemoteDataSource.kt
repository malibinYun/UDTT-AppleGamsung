package com.udtt.applegamsung.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult
import com.udtt.applegamsung.data.remote.firestore.getDocumentSnapshots
import com.udtt.applegamsung.data.source.TestResultsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created By Yun Hyeok
 * on 3월 22, 2020
 */

class TestResultsRemoteDataSource(
    private val firestore: FirebaseFirestore
) : TestResultsDataSource {

    override fun getTestResults(callback: (testResults: List<TestResult>) -> Unit) {
        throw UnsupportedOperationException("Do not call getTestResults() in remoteSource")
    }

    override fun saveTestResult(testResult: TestResult) {
        firestore.collection(PathTestResults)
            .add(testResult)
    }

    override fun getApplePowers(callback: (applePowers: List<ApplePower>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val applePowers = firestore.collection(PathApplePower)
                .getDocumentSnapshots()
                .map {
                    ApplePower(
                        id = it.id,
                        name = it.getString("name").orEmpty(),
                        description = it.getString("description").orEmpty(),
                        minPower = it.getLong("minPower")?.toInt() ?: 0,
                        maxPower = it.getLong("maxPower")?.toInt() ?: 0,
                    )
                }
            callback(applePowers)
        }
    }

    override fun getApplePower(totalScore: Int, callback: (applePower: ApplePower?) -> Unit) {
        throw UnsupportedOperationException("Do not call getApplePower() in remoteSource")
    }

    override fun saveApplePowers(applePowers: List<ApplePower>) {
        throw UnsupportedOperationException("Do not call saveApplePowers() in remoteSource")
    }

    override fun removeAllTestResults() {
        throw UnsupportedOperationException("Do not call removeAllTestResults() in remoteSource")
    }

    companion object {
        private const val PathTestResults = "testResults"
        private const val PathApplePower = "ApplePower"
    }
}
