package com.udtt.applegamsung.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult
import com.udtt.applegamsung.data.source.TestResultsDataSource
import com.udtt.applegamsung.data.remote.firestore.APPLE_POWER_PATH
import com.udtt.applegamsung.data.remote.firestore.TEST_RESULTS_PATH
import com.udtt.applegamsung.data.remote.firestore.getCollection
import com.udtt.applegamsung.data.remote.firestore.toApplePowers
import com.udtt.applegamsung.util.log
import com.udtt.applegamsung.util.showStackTrace

/**
 * Created By Yun Hyeok
 * on 3월 22, 2020
 */

class TestResultsRemoteDataSource(
    private val fireStore: FirebaseFirestore
) : TestResultsDataSource {

    override fun getTestResults(callback: (testResults: List<TestResult>) -> Unit) {
        // No Needed
    }

    override fun saveTestResult(testResult: TestResult) {
        val testResults = fireStore.collection(TEST_RESULTS_PATH)
        testResults.add(testResult)
    }

    override fun getApplePowers(callback: (applePowers: List<ApplePower>) -> Unit) {
        fireStore.getCollection(APPLE_POWER_PATH)
            .addOnSuccessListener { callback(it.toApplePowers()) }
            .addOnFailureListener { log(it.showStackTrace()) }
    }

    override fun getApplePower(totalScore: Int, callback: (applePower: ApplePower?) -> Unit) {
        // No Needed
    }

    override fun saveApplePowers(applePowers: List<ApplePower>) {
        // No Needed
    }

    override fun removeAllTestResults() {
        // No Needed
    }
}
