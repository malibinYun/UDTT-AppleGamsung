package com.udtt.applegamsung.ui.applepower

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.ui.util.SimpleDialog
import com.udtt.applegamsung.R
import com.udtt.applegamsung.databinding.ActivityApplePowerBinding
import com.udtt.applegamsung.ui.intro.IntroActivity
import com.udtt.applegamsung.ui.main.MainActivity
import com.udtt.applegamsung.ui.util.BaseActivity
import org.koin.android.ext.android.inject

class ApplePowerActivity : BaseActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()
    private lateinit var applePowerViewModel: ApplePowerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        applePowerViewModel =
            ViewModelProvider(this, viewModelFactory)[ApplePowerViewModel::class.java]
        applePowerViewModel.checkSavedTestResultOrSave()

        val binding = ActivityApplePowerBinding.inflate(layoutInflater)
        initView(binding)

        val vto = binding.imgApplePower.viewTreeObserver
        vto.addOnPreDrawListener {
            val applePowerImageHeight = binding.imgApplePower.measuredHeight
            binding.imgApplePower.translationY = (applePowerImageHeight / 2).toFloat()
            true
        }
    }

    override fun onBackPressed() {
        backToIntroActivity()
    }

    private fun initView(binding: ActivityApplePowerBinding) {
        setContentView(binding.root)
        initAdmob(binding.banner)
        binding.lifecycleOwner = this
        binding.applePowerViewModel = applePowerViewModel
        binding.btnRetry.setOnClickListener { deployDeleteAlertDialog() }
    }

    private fun deployDeleteAlertDialog() {
        SimpleDialog(this)
            .apply { message = getString(R.string.test_result_will_be_deleted) }
            .setOkClickListener(getString(R.string.its_ok)) { deleteTestResultAndAppleBox() }
            .setCancelClickListener(getString(R.string.its_not_ok)) {}
            .show()
    }

    private fun deleteTestResultAndAppleBox() {
        applePowerViewModel.deleteTestResultAndAppleBox()
        backToMainActivity()
    }

    private fun backToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun backToIntroActivity() {
        val intent = Intent(this, IntroActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
