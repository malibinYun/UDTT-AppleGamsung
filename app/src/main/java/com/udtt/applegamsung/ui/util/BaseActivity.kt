package com.udtt.applegamsung.ui.util

import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

open class BaseActivity : AppCompatActivity() {

    protected fun initAdmob(adView: AdView) {
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }
}