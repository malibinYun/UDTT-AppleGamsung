package com.udtt.applegamsung.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class AppleBoxItem(
    @Embedded
    val product: Product,

    var hasAppleCare: Boolean = false,

    @PrimaryKey
    @ColumnInfo(name = "apple_box_id")
    val id: String = UUID.randomUUID().toString()
) {
    fun getScore(): Double {
        val score = product.score.toDouble()
        return if (hasAppleCare) score * 1.2
        else score
    }
}