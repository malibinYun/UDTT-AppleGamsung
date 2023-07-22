package com.udtt.applegamsung.data.local.mapper

import com.udtt.applegamsung.data.entity.AppleProductEntity
import com.udtt.applegamsung.data.entity.Product

fun AppleProductEntity.toProduct(): Product {
    return Product(
        name = name,
        score = score,
        categoryIndex = sortPriority,
        categoryId = appleProductCategoryId,
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
