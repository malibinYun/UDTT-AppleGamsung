package com.udtt.applegamsung.data.entity

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udtt.applegamsung.R
import java.util.*

/**
 * Created By Yun Hyeok
 * on 3월 14, 2020
 */

@Entity
data class Category(
    val name: String = "",
    val index: Int = 0,

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
) {
    val type: Type
        get() = Type.findByIndex(index)

    enum class Type(val index: Int, val value: String, @DrawableRes val imageRes: Int) {
        MAC(0, "Mac", R.drawable.product_mac),
        IPHONE(1, "iPhone", R.drawable.product_iphone),
        APPLEWATCH(2, "AppleWatch", R.drawable.product_apple_watch),
        IPAD(3, "iPad", R.drawable.product_ipad),
        AIRPODS(4, "AirPods", R.drawable.product_airpdos),
        IPOD(5, "iPod", R.drawable.product_ipod_touch),
        HAVE_NOTING(-1, "", R.drawable.no_apple_imoji);

        companion object {
            fun findByIndex(index: Int): Type = values().first { it.index == index }
        }
    }
}