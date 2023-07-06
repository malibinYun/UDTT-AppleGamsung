package com.udtt.applegamsung.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppleProductEntity(
    val name: String,
    val appleProductCategoryId: String,
    val score: Int,
    val sortPriority: Int,
    val isSelected: Boolean = false,
    val hasAppleCare: Boolean = false,
    @PrimaryKey
    val id: String,
)
