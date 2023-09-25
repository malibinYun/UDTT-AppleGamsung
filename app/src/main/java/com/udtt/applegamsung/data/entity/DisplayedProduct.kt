package com.udtt.applegamsung.data.entity

import com.udtt.applegamsung.domain.model.product.Product

data class DisplayedProduct(
    val product: Product,
    var isSelected: Boolean = false,
    var isInAppleBox: Boolean = false
)
