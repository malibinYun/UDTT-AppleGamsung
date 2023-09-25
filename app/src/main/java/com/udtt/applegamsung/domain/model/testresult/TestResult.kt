package com.udtt.applegamsung.domain.model.testresult

import java.util.Date

/**
 * Created By Yun Hyeok
 * on 3월 14, 2020
 */

data class TestResult(
    val deviceId: String,
    val nickname: String,
    val totalScore: Int,
    val productList: List<String>,
    val timeStamp: Date,
    val id: String,
)
