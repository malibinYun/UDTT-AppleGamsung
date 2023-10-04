package com.udtt.applegamsung.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppleProductCategoryEntity(
    val name: String,
    val index: Int,
    val imageUrl: String,
    @PrimaryKey
    val id: String,
)
