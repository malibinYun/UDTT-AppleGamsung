package com.udtt.applegamsung.data.source

import com.udtt.applegamsung.domain.model.testresult.TestResultProduct

interface TestResultProductsDataSource {

    suspend fun getAllTestResultProducts(): Result<List<TestResultProduct>>

    suspend fun saveTestResultProduct(testResultProducts: List<TestResultProduct>): Result<Unit>
}
