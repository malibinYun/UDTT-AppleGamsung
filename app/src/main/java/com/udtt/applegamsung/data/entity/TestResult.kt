package com.udtt.applegamsung.data.entity

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

/**
 * Created By Yun Hyeok
 * on 3월 14, 2020
 */

@Entity
@IgnoreExtraProperties
data class TestResult(
    val deviceId: String = "",
    val totalScore: Int = 0,

    @ServerTimestamp
    val timeStamp: Date = Date(System.currentTimeMillis())
)