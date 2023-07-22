package com.udtt.applegamsung.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TestResultEntity(
    val deviceId: String,
    val nickname: String,
    val totalScore: Int,
    val timeStamp: Date = Date(System.currentTimeMillis()),
    @PrimaryKey
    val id: String,
)
