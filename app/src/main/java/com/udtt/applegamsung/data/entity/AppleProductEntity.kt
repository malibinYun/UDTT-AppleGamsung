package com.udtt.applegamsung.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppleProductEntity(
    val name: String,
    val appleProductCategoryId: String,
    val score: Int,
    val sortPriority: Int,
    @ColumnInfo(defaultValue = "0") val isInBox: Boolean = false,
    @ColumnInfo(defaultValue = "0") val hasAppleCare: Boolean = false,
    @PrimaryKey val id: String,
)
