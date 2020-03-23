package com.udtt.applegamsung.ui.main.nickname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.databinding.FragmentNicknameBinding
import org.koin.android.ext.android.inject

class NicknameFragment : Fragment() {

    private val viewModelProvider: ViewModelProvider.Factory by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNicknameBinding.inflate(inflater)

        val nicknameViewModel =
            ViewModelProvider(activity!!, viewModelProvider)[NicknameViewModel::class.java]

        binding.nicknameViewModel = nicknameViewModel
        binding.lifecycleOwner = activity
        

        return binding.root
    }

    companion object {
        private var INSTANCE: NicknameFragment? = null
        fun getInstance() = INSTANCE
            ?: NicknameFragment().apply { INSTANCE = this }
    }
}