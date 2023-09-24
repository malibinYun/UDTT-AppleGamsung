package com.udtt.applegamsung.data.remote.mapper

import com.udtt.applegamsung.data.remote.params.SaveTestResultParams
import com.udtt.applegamsung.domain.model.testresult.TestResult

fun TestResult.toSaveResultParams(): SaveTestResultParams {
    return SaveTestResultParams(
        deviceId = deviceId,
        nickname = nickname,
        totalScore = totalScore,
        timeStamp = timeStamp,
        productList = productList,
    )
}
