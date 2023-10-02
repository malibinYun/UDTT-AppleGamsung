package com.udtt.applegamsung.domain.model.category

import androidx.annotation.DrawableRes
import com.udtt.applegamsung.R
import java.util.UUID

/**
 * Created By Yun Hyeok
 * on 3월 14, 2020
 */

data class Category(
    val name: String = "",
    val index: Int = 0,
    val imageUrl: String,
    var id: String = UUID.randomUUID().toString(),
) {
    val type: Type
        get() = Type.findByIndex(index)

    enum class Type(val index: Int, val value: String, @DrawableRes val imageRes: Int) {
        MAC(0, "Mac", R.drawable.product_mac),
        IPHONE(1, "iPhone", R.drawable.product_iphone),
        IPAD(2, "iPad", R.drawable.product_ipad),
        APPLEWATCH(3, "AppleWatch", R.drawable.product_apple_watch),
        AIRPODS(4, "AirPods", R.drawable.product_airpdos),
        IPOD(5, "iPod", R.drawable.product_ipod_touch),
        HAVE_NOTING(6, "", R.drawable.no_apple_imoji);

        companion object {
            fun findByIndex(index: Int): Type = values().first { it.index == index }
        }
    }
}
