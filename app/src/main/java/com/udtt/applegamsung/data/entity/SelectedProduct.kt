package com.udtt.applegamsung.data.entity

/**
 * Created By Yun Hyeok
 * on 3월 27, 2020
 */

data class SelectedProduct(
    val product: Product,
    var hasAppleCare: Boolean = false
) {
    fun toAppleBoxItem(): AppleBoxItem {
        return AppleBoxItem(product, hasAppleCare)
    }

    fun toDisplayedProduct(): DisplayedProduct {
        return DisplayedProduct(product, hasAppleCare)
    }

}