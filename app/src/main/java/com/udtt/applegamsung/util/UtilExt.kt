package com.udtt.applegamsung.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.res.Resources
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView

fun log(message: String) = Log.d("Malibin Debug", message)

fun Exception.showStackTrace(): String {
    return TextUtils.join("\n", this.stackTrace)
}

fun Int.toDp(): Float = this / Resources.getSystem().displayMetrics.density

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Float.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun LottieAnimationView.addAnimationEndListener(listener: (animation: Animator?) -> Unit) {
    this.addAnimatorListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            listener.invoke(animation)
        }
    })
}
