package com.udtt.applegamsung.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.udtt.applegamsung.ui.main.categories.CategoriesFragment
import com.udtt.applegamsung.ui.main.nickname.NicknameFragment
import com.udtt.applegamsung.ui.main.products.ProductsFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        FRAGMENT_NICKNAME -> NicknameFragment.getInstance()
        FRAGMENT_CATEGORIES -> CategoriesFragment.getInstance()
        FRAGMENT_PRODUCTS -> ProductsFragment.getInstance()
        else -> throw IllegalArgumentException("존재할 수 없는 위치의 프래그먼트")
    }

    companion object {
        const val FRAGMENT_NICKNAME = 0
        const val FRAGMENT_CATEGORIES = 1
        const val FRAGMENT_PRODUCTS = 2
        const val FRAGMENT_APPLECARE = 3

        const val FRAGMENT_COUNTS = 4
    }
}