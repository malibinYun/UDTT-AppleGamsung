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
import org.koin.android.ext.android.inject

class AppleBoxActivity : BaseActivity(), AppleBoxItemClickListener {

    private val viewModelFactory: ViewModelProvider.Factory by inject()
    private lateinit var appleBoxViewModel: AppleBoxViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAppleBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)

        appleBoxViewModel = ViewModelProvider(this, viewModelFactory)[AppleBoxViewModel::class.java]

        val adapter = AppleBoxAdapter()
        adapter.setItemClickListener(this)

        binding.lifecycleOwner = this
        binding.appleBoxViewModel = appleBoxViewModel
        binding.rvAppleBoxItems.adapter = adapter
        binding.btnBack.setOnClickListener { finish() }
        binding.btnMyApplePower.setOnClickListener {
            val fadeOutAnim = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            val fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            fadeInAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    val intent = Intent(this@AppleBoxActivity, ApplePowerActivity::class.java)
                    startActivity(intent)
                }

                override fun onAnimationStart(animation: Animation?) {}

            })
            binding.windowAppleBox.startAnimation(fadeOutAnim)
            binding.loadingAnim.visibility = View.VISIBLE
            binding.loadingAnim.startAnimation(fadeInAnim)
        }

        appleBoxViewModel.appleBoxItems.observe(this, Observer {
            adapter.submitList(it)
        })
//        binding.windowAppleBox.animate()
//            .alpha(0f)
//            .setDuration(2000)
//            .withEndAction {
//
//            }
    }

    override fun onAppleBoxItemClick(item: AppleBoxItem) {
        appleBoxViewModel.deleteAppleBoxItem(item)
    }

    private fun initView(binding: ActivityAppleBoxBinding) {
        initAdmob(binding.banner)
    }
}
