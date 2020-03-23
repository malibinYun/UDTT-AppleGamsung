package com.udtt.applegamsung.ui

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("activated")
fun bindActivated(view: View, state: Boolean) {
    view.isActivated = state
}