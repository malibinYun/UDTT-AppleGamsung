package com.udtt.applegamsung.data.local.mapper

import com.udtt.applegamsung.domain.model.testresult.applepower.ApplePower
import com.udtt.applegamsung.data.entity.ApplePowerEntity

fun ApplePowerEntity.toApplePower(): ApplePower {
    return ApplePower(
        name = name,
        description = description,
        minPower = minScore,
        maxPower = maxScore,
        id = id,
    )
}

fun ApplePower.toApplePowerEntity(): ApplePowerEntity {
    return ApplePowerEntity(
        name = name,
        description = description,
        minScore = minPower,
        maxScore = maxPower,
        id = id,
    )
}
