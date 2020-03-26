package com.udtt.applegamsung.ui.main.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.databinding.FragmentCategoriesBinding
import com.udtt.applegamsung.ui.main.MainViewModel
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_NICKNAME
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_PRODUCTS
import com.udtt.applegamsung.ui.util.BaseFragment
import org.koin.android.ext.android.inject

class CategoriesFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCategoriesBinding.inflate(layoutInflater)

        val mainViewModel =
            ViewModelProvider(activity!!, viewModelFactory)[MainViewModel::class.java]
        val categoriesViewModel =
            ViewModelProvider(this, viewModelFactory)[CategoriesViewModel::class.java]

        val categoriesAdapter = createCategoriesAdapter(mainViewModel)
        binding.categories.adapter = categoriesAdapter

        initView(binding, mainViewModel)
        subscribeCategories(categoriesViewModel, categoriesAdapter)

        return binding.root
    }

    private fun initView(binding: FragmentCategoriesBinding, mainViewModel: MainViewModel) {
        binding.btnBack.setOnClickListener { mainViewModel.movePageTo(FRAGMENT_NICKNAME) }
        initAdmob(binding.banner)
    }

    private fun createCategoriesAdapter(mainViewModel: MainViewModel): CategoriesAdapter {
        return CategoriesAdapter().apply { dataBindingWith(mainViewModel, activity!!) }
    }

    private fun subscribeCategories(viewModel: CategoriesViewModel, adapter: CategoriesAdapter) {
        viewModel.categories.observe(this, Observer {
            adapter.initCategories(it)
        })
    }

    companion object {
        private var INSTANCE: CategoriesFragment? = null
        fun getInstance() = INSTANCE
            ?: CategoriesFragment().apply { INSTANCE = this }
    }
}
