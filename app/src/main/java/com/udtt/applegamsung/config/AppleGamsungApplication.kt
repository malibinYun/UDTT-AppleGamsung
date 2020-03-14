package com.udtt.applegamsung.config

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.udtt.applegamsung.R
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created By Yun Hyeok
 * on 3월 14, 2020
 */

class AppleGamsungApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppleGamsungApplication)
        }

        MobileAds.initialize(this, getString(R.string.admobSdkId))
    }

}