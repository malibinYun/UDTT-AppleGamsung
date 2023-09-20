package com.udtt.applegamsung.domain.model.product

import com.google.firebase.firestore.IgnoreExtraProperties
import com.udtt.applegamsung.domain.model.category.Category
import com.udtt.applegamsung.data.entity.SelectedProduct
import java.util.UUID

/**
 * Created By Yun Hyeok
 * on 3월 14, 2020
 */

@IgnoreExtraProperties
data class Product(
    val name: String = "",
    val score: Int = 0,
    val categoryIndex: Int = 0,
    var categoryId: String = "",
    val imageUrl: String = "",
    val id: String = UUID.randomUUID().toString()
) {
    var imageByteArray: ByteArray? = null

    val categoryType: Category.Type
        get() = Category.Type.findByIndex(categoryIndex)

    fun toSelectedProduct(): SelectedProduct {
        return SelectedProduct(this)
    }
}
