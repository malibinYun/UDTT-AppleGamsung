package com.udtt.applegamsung.ui.util

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.load
import com.udtt.applegamsung.R

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

@BindingAdapter("fade_in_visible")
fun bindVisibilityFadeIn(view: View, isLoading: Boolean?) {
    if (isLoading != false) return
    view.animate()
        .setDuration(1_000)
        .alpha(1f)
}

@BindingAdapter("anim_purse")
fun bindAnimationPurse(view: View, state: Boolean?) {
    if (state != true) return
    val anim = AnimationUtils.loadAnimation(view.context, R.anim.anim_purse)
    view.startAnimation(anim)
}

@BindingAdapter("imageUrl")
fun bindImageUrl(imageView: ImageView, imageUrl: String?) {
    imageView.load(imageUrl ?: return)
}
