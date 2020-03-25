package com.udtt.applegamsung.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class SelectedProduct(
    @Embedded
    val product: Product,

    var hasAppleCare: Boolean = false,

    @PrimaryKey
    @ColumnInfo(name = "selected_product_id")
    val id: String = UUID.randomUUID().toString()
)