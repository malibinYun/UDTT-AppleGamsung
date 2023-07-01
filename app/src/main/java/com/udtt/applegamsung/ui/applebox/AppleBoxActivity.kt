package com.udtt.applegamsung.ui.applebox

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.udtt.applegamsung.R
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.databinding.ActivityAppleBoxBinding
import com.udtt.applegamsung.ui.applepower.ApplePowerActivity
import com.udtt.applegamsung.ui.main.categories.CategoriesFragment
import com.udtt.applegamsung.ui.util.BaseActivity
import com.udtt.applegamsung.ui.util.SimpleDialog
import com.udtt.applegamsung.util.addAnimationEndListener
import org.koin.android.ext.android.inject

class AppleBoxActivity : BaseActivity(), AppleBoxItemClickListener {

    private val viewModelFactory: ViewModelProvider.Factory by inject()
    private lateinit var appleBoxViewModel: AppleBoxViewModel

    private lateinit var binding: ActivityAppleBoxBinding
    private lateinit var interstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appleBoxViewModel = ViewModelProvider(this, viewModelFactory)[AppleBoxViewModel::class.java]
        binding = ActivityAppleBoxBinding.inflate(layoutInflater)
        val adapter = AppleBoxAdapter().apply { setItemClickListener(this@AppleBoxActivity) }
        initView(adapter)

        checkHaveNothing()

        subscribeAppleBoxItems(adapter)
    }

    override fun onBackPressed() {
        val isAnimating = binding.loadingAnim.isAnimating
        if (!isAnimating) {
            super.onBackPressed()
        }
    }

    override fun onAppleBoxItemClick(item: AppleBoxItem) {
        appleBoxViewModel.deleteAppleBoxItem(item)
    }

    override fun initAdmob(adView: AdView) {
        super.initAdmob(adView)

        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            getString(R.string.admobInterstitialId),
            adRequest,
            createAdClosedListener(),
        )
    }

    private fun createAdClosedListener(): InterstitialAdLoadCallback =
        object : InterstitialAdLoadCallback() {

            override fun onAdLoaded(loadedInterstitialAd: InterstitialAd) {
                super.onAdLoaded(loadedInterstitialAd)

                loadedInterstitialAd.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        deployApplePowerActivity()
                    }
                }
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                super.onAdFailedToLoad(loadAdError)
                Toast.makeText(
                    this@AppleBoxActivity,
                    "몬가... 몬가 에러가 났음... 다시 시도해주세요...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private fun initView(adapter: AppleBoxAdapter) {
        setContentView(binding.root)
        initAdmob(binding.banner)
        binding.lifecycleOwner = this
        binding.appleBoxViewModel = appleBoxViewModel
        binding.rvAppleBoxItems.adapter = adapter
        binding.btnBack.setOnClickListener { finish() }
        binding.btnMyApplePower.setOnClickListener { deployDeleteAlertDialog() }
    }

    private fun startAnimation() {
        startFadeAnimation()
        startLottieAnimation()
    }

    private fun startFadeAnimation() {
        binding.windowAppleBox.animate()
            .setDuration(ANIM_DURATION)
            .alpha(0f)
        binding.loadingAnim.animate()
            .setDuration(ANIM_DURATION)
            .alpha(1f)
    }

    private fun startLottieAnimation() {
        binding.loadingAnim.speed = 1.5f
        binding.loadingAnim.playAnimation()
        binding.loadingAnim.addAnimationEndListener { interstitialAd.show(this) }
    }

    private fun deployApplePowerActivity() {
        val intent = Intent(this@AppleBoxActivity, ApplePowerActivity::class.java)
        startActivity(intent)
    }

    private fun checkHaveNothing() {
        val haveNothing = intent.getBooleanExtra(CategoriesFragment.EXTRA_HAVE_NOTHING, false)
        if (haveNothing) {
            deployDeleteAlertDialog()
        }
    }

    private fun deployDeleteAlertDialog() {
        val itemCount = appleBoxViewModel.appleBoxItems.value?.size ?: 0
        SimpleDialog(this)
            .apply { message = getString(R.string.i_will_calculate_apple_power, itemCount) }
            .setOkClickListener(getString(R.string.yes)) { startAnimation() }
            .setCancelClickListener(getString(R.string.no)) {}
            .show()
    }

    private fun subscribeAppleBoxItems(adapter: AppleBoxAdapter) {
        appleBoxViewModel.appleBoxItems.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    companion object {
        const val ANIM_DURATION = 1_000L
    }
}
