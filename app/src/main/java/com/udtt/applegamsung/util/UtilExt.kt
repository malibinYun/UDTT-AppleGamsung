package com.udtt.applegamsung.util

import android.content.res.Resources
import android.text.TextUtils
import android.util.Log

fun log(message: String) = Log.d("Malibin Debug", message)

fun Exception.showStackTrace(): String {
    return TextUtils.join("\n", this.stackTrace)
}

fun Int.toDp(): Float = this / Resources.getSystem().displayMetrics.density

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Float.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()