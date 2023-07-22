package com.udtt.applegamsung.data.entity

import java.util.*

data class AppleBoxItem(
    val product: Product,
    var hasAppleCare: Boolean = false,
    val id: String = UUID.randomUUID().toString()
) {
    fun getScore(): Double {
        val score = product.score.toDouble()
        return if (hasAppleCare) score * 1.2
        else score
    }
}
