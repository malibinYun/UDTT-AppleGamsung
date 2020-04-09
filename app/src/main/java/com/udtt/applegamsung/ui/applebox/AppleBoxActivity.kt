package com.udtt.applegamsung.ui.applebox

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.R
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.databinding.ActivityAppleBoxBinding
import com.udtt.applegamsung.ui.applepower.ApplePowerActivity
import com.udtt.applegamsung.ui.util.BaseActivity
import com.udtt.applegamsung.ui.util.SimpleDialog
import org.koin.android.ext.android.inject

class AppleBoxActivity : BaseActivity(), AppleBoxItemClickListener {

    private val viewModelFactory: ViewModelProvider.Factory by inject()
    private lateinit var appleBoxViewModel: AppleBoxViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appleBoxViewModel = ViewModelProvider(this, viewModelFactory)[AppleBoxViewModel::class.java]

        val adapter = AppleBoxAdapter()
        adapter.setItemClickListener(this)

        val binding = ActivityAppleBoxBinding.inflate(layoutInflater)
        initView(binding, adapter)

        subscribeAppleBoxItems(adapter)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        // TODO: 애니메이션 중일때 안누리는 로직 작성해야함
    }

    override fun onAppleBoxItemClick(item: AppleBoxItem) {
        appleBoxViewModel.deleteAppleBoxItem(item)
    }

    private fun initView(binding: ActivityAppleBoxBinding, adapter: AppleBoxAdapter) {
        setContentView(binding.root)
        initAdmob(binding.banner)
        binding.lifecycleOwner = this
        binding.appleBoxViewModel = appleBoxViewModel
        binding.rvAppleBoxItems.adapter = adapter
        binding.btnBack.setOnClickListener { finish() }
        binding.btnMyApplePower.setOnClickListener { deployDeleteAlertDialog(binding) }
    }

    private fun startFadeAnimation(binding: ActivityAppleBoxBinding) {
        binding.windowAppleBox.animate()
            .alpha(0f)
            .setDuration(ANIM_DURATION)
            .withEndAction { deployApplePowerActivity() }
        binding.loadingAnim.animate()
            .alpha(1f)
            .setDuration(ANIM_DURATION)
            .withEndAction {
                binding.loadingAnim.visibility = View.VISIBLE
            }
    }

    private fun deployApplePowerActivity() {
        val intent = Intent(this@AppleBoxActivity, ApplePowerActivity::class.java)
        startActivity(intent)
    }

    private fun deployDeleteAlertDialog(binding: ActivityAppleBoxBinding) {
        val itemCount = appleBoxViewModel.appleBoxItems.value?.size
        SimpleDialog(this)
            .apply { message = getString(R.string.i_will_calculate_apple_power, itemCount) }
            .setOkClickListener(getString(R.string.yes)) { startFadeAnimation(binding) }
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
