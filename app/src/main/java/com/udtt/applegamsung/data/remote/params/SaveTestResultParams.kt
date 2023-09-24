package com.udtt.applegamsung.data.remote.params

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class SaveTestResultParams(
    val deviceId: String,
    val nickname: String,
    val totalScore: Int,
    @ServerTimestamp
    val productList: List<String>,
    val timeStamp: Date,
)
