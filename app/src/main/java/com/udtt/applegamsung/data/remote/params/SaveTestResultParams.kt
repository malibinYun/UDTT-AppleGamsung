package com.udtt.applegamsung.data.remote.params

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class SaveTestResultParams(
    val deviceId: String,
    val nickname: String,
    val totalScore: Int,
    val productList: List<String>,
    @ServerTimestamp
    val timeStamp: Date,
)
