package com.udtt.applegamsung.ui.main.nickname

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.databinding.FragmentNicknameBinding
import com.udtt.applegamsung.ui.main.MainViewModel
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_CATEGORIES
import com.udtt.applegamsung.ui.util.BaseFragment
import com.udtt.applegamsung.util.AsyncExecutor
import org.koin.android.ext.android.inject

class NicknameFragment : BaseFragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var nicknameViewModel: NicknameViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainViewModel = ViewModelProvider(activity!!, viewModelFactory)[MainViewModel::class.java]
        nicknameViewModel = ViewModelProvider(this, viewModelFactory)[NicknameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNicknameBinding.inflate(inflater)
        initView(binding)

        return binding.root
    }

    private fun initView(binding: FragmentNicknameBinding) {
        binding.nicknameViewModel = nicknameViewModel
        binding.lifecycleOwner = activity

        binding.btnNext.setOnClickListener {
            mainViewModel.movePageTo(FRAGMENT_CATEGORIES)
            nicknameViewModel.saveNickname()
            hideSoftKeyBoard()
        }
        binding.btnBack.setOnClickListener { activity?.finish() }

        initAdmob(binding.banner)
    }

    private fun hideSoftKeyBoard() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.rootView?.windowToken, 0)
    }

    companion object {
        private var INSTANCE: NicknameFragment? = null
        fun getInstance() = INSTANCE
            ?: NicknameFragment().apply { INSTANCE = this }
    }
}