package com.udtt.applegamsung.ui.applepower

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.ui.util.SimpleDialog
import com.udtt.applegamsung.R
import com.udtt.applegamsung.databinding.ActivityApplePowerBinding
import com.udtt.applegamsung.ui.main.MainActivity
import com.udtt.applegamsung.ui.util.BaseActivity
import org.koin.android.ext.android.inject

class ApplePowerActivity : BaseActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()
    private lateinit var applePowerViewModel: ApplePowerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityApplePowerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(binding)

        applePowerViewModel =
            ViewModelProvider(this, viewModelFactory)[ApplePowerViewModel::class.java]

        val vto = binding.imgApplePower.viewTreeObserver
        vto.addOnPreDrawListener {
            val applePowerImageHeight = binding.imgApplePower.measuredHeight
            binding.imgApplePower.translationY = (applePowerImageHeight / 2).toFloat()
            true
        }
    }

    private fun initView(binding: ActivityApplePowerBinding) {
        initAdmob(binding.banner)
        binding.lifecycleOwner = this
        binding.applePowerViewModel = applePowerViewModel
        binding.btnRetry.setOnClickListener { showDeleteAlertDialog() }
    }

    private fun showDeleteAlertDialog() {
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
    }
}
