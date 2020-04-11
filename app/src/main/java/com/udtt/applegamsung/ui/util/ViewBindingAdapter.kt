package com.udtt.applegamsung.ui.util

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

@BindingAdapter("activated")
fun bindActivated(view: View, state: Boolean) {
    view.isActivated = state
}

@BindingAdapter("selected")
fun bindSelected(view: View, state: Boolean) {
    view.isSelected = state
}

@BindingAdapter("img_res")
fun bindImageByResId(imageView: ImageView, @DrawableRes resId: Int) {
    imageView.setImageResource(resId)
}