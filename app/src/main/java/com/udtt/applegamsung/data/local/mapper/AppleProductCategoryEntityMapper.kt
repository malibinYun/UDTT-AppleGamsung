package com.udtt.applegamsung.data.local.mapper

import com.udtt.applegamsung.data.entity.AppleProductCategoryEntity
import com.udtt.applegamsung.domain.model.category.Category

fun AppleProductCategoryEntity.toCategory(): Category {
    return Category(
        name = name,
        index = index,
        imageUrl = imageUrl,
        id = id,
    )
}

fun Category.toAppleProductCategoryEntity(): AppleProductCategoryEntity {
    return AppleProductCategoryEntity(
        name = name,
        index = index,
        imageUrl = imageUrl,
        id = id,
    )
}
