package com.udtt.applegamsung.ui.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("text_with_new_line")
fun bindTextWithNewLine(textView: TextView, string: String?) {
    val convertedString = string?.replace("\\n", "\n") ?: ""
    textView.text = convertedString
}

@BindingAdapter("char_wrap_text")
fun bindTextWrappedByChar(textView: TextView, string: String?) {
    val convertedString = string?.replace(" ", "\u00A0") ?: ""
    textView.text = convertedString
}