package com.udtt.applegamsung.data.local.mapper

import com.udtt.applegamsung.domain.model.testresult.TestResult
import com.udtt.applegamsung.data.entity.TestResultEntity

fun TestResultEntity.toTestResult(): TestResult {
    return TestResult(
        deviceId = deviceId,
        nickname = nickname,
        totalScore = totalScore,
        timeStamp = timeStamp,
        id = id,
    )
}

fun TestResult.toTestResultEntity(): TestResultEntity {
    return TestResultEntity(
        deviceId = deviceId,
        nickname = nickname,
        totalScore = totalScore,
        timeStamp = timeStamp,
        id = id,
    )
}
