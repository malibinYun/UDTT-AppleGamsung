package com.udtt.applegamsung.data.local.mapper

import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.entity.AppleProductEntity


fun AppleProductEntity.toAppleBoxItem(): AppleBoxItem {
    return AppleBoxItem(
        product = this.toProduct(),
        hasAppleCare = hasAppleCare,
        id = id,
    )
}

fun AppleBoxItem.toAppleProductEntity(isInBox: Boolean): AppleProductEntity {
    return AppleProductEntity(
        name = product.name,
        appleProductCategoryId = product.categoryId,
        score = product.score,
        sortPriority = product.categoryIndex,
        isInBox = isInBox,
        hasAppleCare = hasAppleCare,
        id = product.id,
    )
}
