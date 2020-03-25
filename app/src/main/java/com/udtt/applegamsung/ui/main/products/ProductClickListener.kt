package com.udtt.applegamsung.ui.main.products

import com.udtt.applegamsung.data.entity.DisplayedProduct

/**
 * Created By Yun Hyeok
 * on 3월 24, 2020
 */

interface ProductClickListener {

    fun onProductClick(displayedProduct: DisplayedProduct, isSelected: Boolean)

}