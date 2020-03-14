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
    val name: String = ""
) {
    // 카테고리 인덱스를 두던, 카테고리 다큐먼트 id를 두던 카테고리를 식별할 무언가를 두는게 낫겟다.
    // 그래야 room에 집어넣을때 카테고리별로 product들을 관리할수잇으니까
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