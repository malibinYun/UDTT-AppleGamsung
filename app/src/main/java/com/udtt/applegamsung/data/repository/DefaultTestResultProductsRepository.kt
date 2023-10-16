package com.udtt.applegamsung.data.repository

import com.udtt.applegamsung.data.source.TestResultProductsDataSource
import com.udtt.applegamsung.domain.model.testresult.TestResultProduct
import com.udtt.applegamsung.domain.repository.TestResultProductsRepository
import com.udtt.applegamsung.util.getOrEmpty

class DefaultTestResultProductsRepository(
    private val localTestResultProductsDataSource: TestResultProductsDataSource,
    private val remoteTestResultProductsDataSource: TestResultProductsDataSource,
) : TestResultProductsRepository {

    override suspend fun getAllTestResultProducts(): Result<List<TestResultProduct>> {
        val localTestResultProducts = localTestResultProductsDataSource.getAllTestResultProducts()
        if (localTestResultProducts.getOrEmpty().isNotEmpty()) {
            return localTestResultProducts
        }
        return remoteTestResultProductsDataSource.getAllTestResultProducts()
            .onSuccess { localTestResultProductsDataSource.saveTestResultProduct(it) }
    }

    override suspend fun saveTestResultProduct(testResultProducts: List<TestResultProduct>): Result<Unit> {
        return localTestResultProductsDataSource.saveTestResultProduct(testResultProducts)
    }
}
