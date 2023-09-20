package com.udtt.applegamsung.domain.model.testresult

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

/**
 * Created By Yun Hyeok
 * on 3월 14, 2020
 */

@IgnoreExtraProperties
data class TestResult(
    val deviceId: String,
    val nickname: String,
    val totalScore: Int,
    @ServerTimestamp
    val timeStamp: Date = Date(System.currentTimeMillis()),
    val id: String = UUID.randomUUID().toString()
) {
    var productList: List<String> = emptyList()
}
