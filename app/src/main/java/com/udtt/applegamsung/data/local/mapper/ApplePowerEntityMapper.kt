package com.udtt.applegamsung.data.local.mapper

import com.udtt.applegamsung.data.entity.ApplePowerEntity
import com.udtt.applegamsung.domain.model.testresult.applepower.ApplePower

fun ApplePowerEntity.toApplePower(): ApplePower {
    return ApplePower(
        name = name,
        description = description,
        minPower = minScore,
        maxPower = maxScore,
        imageUrl = imageUrl,
        id = id,
    )
}

fun ApplePower.toApplePowerEntity(): ApplePowerEntity {
    return ApplePowerEntity(
        name = name,
        description = description,
        minScore = minPower,
        maxScore = maxPower,
        imageUrl = imageUrl,
        id = id,
    )
}
