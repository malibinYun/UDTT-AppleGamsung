package com.udtt.applegamsung.data.source.local

import com.udtt.applegamsung.data.dao.TestResultProductsDao
import com.udtt.applegamsung.data.local.mapper.toTestResultProduct
import com.udtt.applegamsung.data.local.mapper.toTestResultProductEntity
import com.udtt.applegamsung.data.source.TestResultProductsDataSource
import com.udtt.applegamsung.domain.model.testresult.TestResultProduct
import com.udtt.applegamsung.util.mapList

class LocalTestResultProductsDataSource(
    private val testResultProductsDao: TestResultProductsDao,
) : TestResultProductsDataSource {

    override suspend fun getAllTestResultProducts(): Result<List<TestResultProduct>> {
        return runCatching { testResultProductsDao.getAllTestResultProducts() }
            .mapList { it.toTestResultProduct() }
    }

    override suspend fun saveTestResultProduct(testResultProducts: List<TestResultProduct>): Result<Unit> {
        return runCatching {
            testResultProductsDao.insertTestResultProducts(
                testResultProducts.map { it.toTestResultProductEntity() })
        }
    }
}
