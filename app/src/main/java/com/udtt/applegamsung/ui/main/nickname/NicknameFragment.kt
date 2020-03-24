package com.udtt.applegamsung.ui.main.nickname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.databinding.FragmentNicknameBinding
import com.udtt.applegamsung.ui.main.MainViewModel
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_CATEGORIES
import org.koin.android.ext.android.inject

class NicknameFragment : Fragment() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()
    private lateinit var mainViewModel: MainViewModel
    private lateinit var nicknameViewModel: NicknameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNicknameBinding.inflate(inflater)

        mainViewModel = ViewModelProvider(activity!!, viewModelFactory)[MainViewModel::class.java]
        nicknameViewModel = ViewModelProvider(this, viewModelFactory)[NicknameViewModel::class.java]

        initView(binding)

        return binding.root
    }

    private fun initView(binding: FragmentNicknameBinding) {
        binding.nicknameViewModel = nicknameViewModel
        binding.lifecycleOwner = activity

        binding.btnNext.setOnClickListener {
            mainViewModel.movePageTo(FRAGMENT_CATEGORIES)
            nicknameViewModel.saveNickname()
        }
        binding.btnBack.setOnClickListener { activity?.finish() }
    }

    companion object {
        private var INSTANCE: NicknameFragment? = null
        fun getInstance() = INSTANCE
            ?: NicknameFragment().apply { INSTANCE = this }
    }
}