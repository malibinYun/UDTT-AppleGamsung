package com.udtt.applegamsung.data.local.mapper

import com.udtt.applegamsung.data.entity.AppleProductEntity
import com.udtt.applegamsung.domain.model.product.Product

fun AppleProductEntity.toProduct(): Product {
    return Product(
        name = name,
        score = score,
        categoryIndex = sortPriority,
        categoryId = appleProductCategoryId,
        imageUrl = "",
        id = id,
    )
}

fun Product.toAppleProductEntity(): AppleProductEntity {
    return AppleProductEntity(
        name = name,
        appleProductCategoryId = categoryId,
        score = score,
        sortPriority = categoryIndex,
        id = id,
    )
}
