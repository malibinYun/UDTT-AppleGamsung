package com.udtt.applegamsung.ui.main

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.udtt.applegamsung.R
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.databinding.ActivityMainBinding
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_COUNTS
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_NICKNAME
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        initView(binding)

        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        subscribeCurrentPage(binding)
        subscribeSavedProducts()
    }

    override fun onBackPressed() {
        val currentPage = getCurrentPage()
        if (currentPage == FRAGMENT_NICKNAME) {
            super.onBackPressed()
            return
        }
        mainViewModel.movePageTo(currentPage - 1)
    }

    private fun getCurrentPage(): Int {
        return mainViewModel.currentPage.value
            ?: throw IllegalStateException("currentPage는 null일 수 없음")
    }

    private fun initView(binding: ActivityMainBinding) {
        setContentView(binding.root)
        binding.vpMain.adapter = MainViewPagerAdapter(this)
        binding.vpMain.offscreenPageLimit = FRAGMENT_COUNTS
        binding.vpMain.isUserInputEnabled = false
    }

    private fun subscribeCurrentPage(binding: ActivityMainBinding) {
        mainViewModel.currentPage.observe(this, Observer {
            binding.vpMain.currentItem = it
        })
    }

    private fun subscribeSavedProducts() {
        mainViewModel.savedProducts.observe(this, Observer {
            showProductsSavedSnackBar(it)
        })
    }

    private fun showProductsSavedSnackBar(products: List<AppleBoxItem>) {
        val message = getString(R.string.n_products_are_contained, products.size)
        val actionMessage = getString(R.string.undo)
        showSnackBar(message, actionMessage) { undoSaveProducts(products) }
    }

    private fun showSnackBar(
        message: String,
        actionMessage: String = "",
        actionClickListener: (() -> Unit)? = null
    ) {
        val view = findViewById<View>(R.id.vp_main)
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        if (actionClickListener != null) {
            snackBar.setAction(actionMessage) { actionClickListener.invoke() }
        }
        snackBar.show()
    }

    private fun showSnackBar(
        @StringRes resId: Int,
        actionMessage: String = "",
        actionClickListener: (() -> Unit)? = null
    ) {
        val message = getString(resId)
        showSnackBar(message, actionMessage, actionClickListener)
    }

    private fun undoSaveProducts(products: List<AppleBoxItem>) {
        mainViewModel.undoSavedProducts(products)
        showSnackBar(R.string.undo_done)
    }
}
