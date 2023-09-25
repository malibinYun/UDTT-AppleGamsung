package com.udtt.applegamsung.domain.model.product

import com.udtt.applegamsung.domain.model.category.Category
import com.udtt.applegamsung.data.entity.SelectedProduct

/**
 * Created By Yun Hyeok
 * on 3월 14, 2020
 */

data class Product(
    val name: String,
    val score: Int,
    val categoryIndex: Int,
    val categoryId: String,
    val imageUrl: String,
    val id: String,
) {
    val categoryType: Category.Type
        get() = Category.Type.findByIndex(categoryIndex)

    fun toSelectedProduct(): SelectedProduct {
        return SelectedProduct(this)
    }
}
