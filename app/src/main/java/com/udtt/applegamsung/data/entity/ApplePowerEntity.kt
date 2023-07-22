package com.udtt.applegamsung.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ApplePowerEntity(
    val name: String,
    val description: String,
    val minScore: Int,
    val maxScore: Int,
    @PrimaryKey
    val id: String,
)
