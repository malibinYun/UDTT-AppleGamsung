package com.udtt.applegamsung.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class ApplePower(
    val name: String = "",
    val description: String = "",
    val maxPower: Int = 0,

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
)