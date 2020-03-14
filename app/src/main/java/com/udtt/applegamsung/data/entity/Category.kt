package com.udtt.applegamsung.data.entity

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Created By Yun Hyeok
 * on 3월 14, 2020
 */

@Entity
@IgnoreExtraProperties
data class Category(
    val name: String
) {
    enum class Type(val index: Int, val value: String) {
        MAC(0, "Mac"),
        IPHONE(1, "iPhone"),
        APPLEWATCH(2, "AppleWatch"),
        IPAD(3, "iPad"),
        AIRPODS(4, "AirPods"),
        IPOD(5, "iPod");

        companion object {
            fun findByIndex(index: Int): Type = values().first { it.index == index }
        }
    }
}