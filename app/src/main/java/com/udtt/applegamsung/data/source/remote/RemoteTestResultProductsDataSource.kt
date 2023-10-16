package com.udtt.applegamsung.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.remote.firestore.getDocumentSnapshots
import com.udtt.applegamsung.data.source.TestResultProductsDataSource
import com.udtt.applegamsung.domain.model.testresult.TestResultProduct

class RemoteTestResultProductsDataSource(
    private val firestore: FirebaseFirestore,
) : TestResultProductsDataSource {

    override suspend fun getAllTestResultProducts(): Result<List<TestResultProduct>> {
        return firestore.collection(PathTestResultProduct)
            .getDocumentSnapshots()
            .map { documents ->
                documents.map {
                    TestResultProduct(
                        id = it.id,
                        name = it.getString("name").orEmpty(),
                        categoryId = it.getString("categoryId").orEmpty(),
                        productId = it.getString("productId").orEmpty(),
                        orderIndex = it.getLong("name")?.toInt() ?: 0,
                    )
                }
            }
    }

    override suspend fun saveTestResultProduct(testResultProducts: List<TestResultProduct>): Result<Unit> {
        return Result.failure(
            UnsupportedOperationException("Do not call saveTestResultProduct() in remoteSource")
        )
    }

    companion object {
        private const val PathTestResultProduct = "TestResultProduct"
    }
}
