package com.udtt.applegamsung.domain.repository

import com.udtt.applegamsung.domain.model.testresult.TestResultProduct

interface TestResultProductsRepository {

    suspend fun getAllTestResultProducts(): Result<List<TestResultProduct>>

    suspend fun saveTestResultProduct(testResultProducts: List<TestResultProduct>): Result<Unit>
}
