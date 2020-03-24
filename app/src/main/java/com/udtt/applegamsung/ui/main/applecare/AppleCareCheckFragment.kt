package com.udtt.applegamsung.ui.main.applecare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.udtt.applegamsung.databinding.FragmentCheckApplecareBinding

/**
 * Created By Yun Hyeok
 * on 3월 24, 2020
 */

class AppleCareCheckFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCheckApplecareBinding.inflate(inflater)

        

        return binding.root
    }

    companion object {
        private var INSTANCE: AppleCareCheckFragment? = null
        fun getInstance() = INSTANCE
            ?: AppleCareCheckFragment().apply { INSTANCE = this }
    }
}