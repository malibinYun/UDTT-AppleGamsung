package com.udtt.applegamsung.domain.model.product

data class Products(
    private val products: List<Product>,
) {
    private val productIds: List<String> by lazy { products.map { it.id } }
    private val categoryIds: List<String> by lazy { products.map { it.categoryId } }

    fun getValues(): List<Product> = products.toList()

    fun isEmpty(): Boolean = products.isEmpty()

    fun isNotEmpty(): Boolean = products.isNotEmpty()

    fun hasProductOf(productId: String): Boolean = productId in productIds

    fun hasCategoryOf(categoryId: String): Boolean = categoryId in categoryIds

    fun sumScores(): Int = products.sumOf { it.score }

    fun havingCategoriesCount(): Int = categoryIds.size
}
