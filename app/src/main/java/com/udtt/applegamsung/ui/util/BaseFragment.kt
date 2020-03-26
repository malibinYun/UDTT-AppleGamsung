package com.udtt.applegamsung.ui.util

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.android.ext.android.inject

open class BaseFragment : Fragment() {

    protected val viewModelFactory: ViewModelProvider.Factory by inject()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        trackThisFragmentFromGA(context)
    }

    protected fun initAdmob(adView: AdView) {
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    private fun trackThisFragmentFromGA(context: Context) {
        FirebaseAnalytics.getInstance(context)
            .setCurrentScreen(activity!!, javaClass.simpleName, javaClass.simpleName)
    }
}