package com.udtt.applegamsung.data.entity

data class DisplayedProduct(
    val product: Product,
    var isSelected: Boolean = false,
    var isInAppleBox: Boolean = false
)