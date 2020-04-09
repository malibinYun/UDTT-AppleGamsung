package com.udtt.applegamsung.ui.intro

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.TestResult
import com.udtt.applegamsung.data.util.CATEGORIES_PATH
import com.udtt.applegamsung.databinding.ActivityIntroBinding
import com.udtt.applegamsung.ui.applepower.ApplePowerActivity
import com.udtt.applegamsung.ui.main.MainActivity
import com.udtt.applegamsung.ui.util.BaseActivity
import com.udtt.applegamsung.util.log
import org.koin.android.ext.android.inject

class IntroActivity : BaseActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()
    private lateinit var introViewModel: IntroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityIntroBinding.inflate(layoutInflater)
        initView(binding)

        introViewModel = ViewModelProvider(this, viewModelFactory)[IntroViewModel::class.java]
        introViewModel.checkDeviceId()
    }

    override fun onResume() {
        super.onResume()
        introViewModel.loadAppleBoxIsEmpty()
    }

    private fun initView(binding: ActivityIntroBinding) {
        setContentView(binding.root)
        setStatusBarTransparent()
        initAdmob(binding.banner)
        binding.btnStart.setOnClickListener { deployActivity() }
    }

    private fun deployActivity() {
        if (introViewModel.isBoxEmpty) deployMainActivity()
        else deployApplePowerActivity()
    }

    private fun deployMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun deployApplePowerActivity() {
        val intent = Intent(this, ApplePowerActivity::class.java)
        startActivity(intent)
    }

    private fun setStatusBarTransparent() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        if (Build.VERSION.SDK_INT >= 21) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
            return
        }
        winParams.flags = winParams.flags and bits.inv()
        win.attributes = winParams
    }
}
