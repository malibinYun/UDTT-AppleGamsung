package com.udtt.applegamsung.data.local.mapper

import com.udtt.applegamsung.data.entity.TestResultProductEntity
import com.udtt.applegamsung.domain.model.testresult.TestResultProduct

fun TestResultProductEntity.toTestResultProduct(): TestResultProduct {
    return TestResultProduct(
        id = id,
        name = name,
        categoryId = categoryId,
        productId = productId,
        orderIndex = orderIndex,
    )
}

fun TestResultProduct.toTestResultProductEntity(): TestResultProductEntity {
    return TestResultProductEntity(
        id = id,
        name = name,
        categoryId = categoryId,
        productId = productId,
        orderIndex = orderIndex,
    )
}
