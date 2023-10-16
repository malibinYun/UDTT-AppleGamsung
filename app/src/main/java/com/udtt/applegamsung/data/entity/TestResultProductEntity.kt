package com.udtt.applegamsung.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestResultProductEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val categoryId: String,
    val productId: String?,
    val orderIndex: Int,
)
