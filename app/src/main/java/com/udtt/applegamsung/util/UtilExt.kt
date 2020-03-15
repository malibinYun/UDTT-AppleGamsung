package com.udtt.applegamsung.util

import android.text.TextUtils
import android.util.Log

fun log(message: String) = Log.d("Malibin Debug", message)

fun Exception.showStackTrace(): String {
    return TextUtils.join("\n", this.stackTrace)
}
