package com.udtt.applegamsung.domain.model.testresult

data class TestResultProduct(
    val id: String,
    val name: String,
    val categoryId: String,
    val productId: String?,
    val orderIndex: Int,
) {
    val isCategory: Boolean = productId == null
    val isProduct: Boolean = productId != null
}
