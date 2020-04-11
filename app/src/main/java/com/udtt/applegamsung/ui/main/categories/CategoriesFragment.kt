package com.udtt.applegamsung.ui.main.categories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.databinding.FragmentCategoriesBinding
import com.udtt.applegamsung.ui.applebox.AppleBoxActivity
import com.udtt.applegamsung.ui.main.MainViewModel
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_NICKNAME
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_PRODUCTS
import com.udtt.applegamsung.ui.util.BaseFragment
import org.koin.android.ext.android.inject

class CategoriesFragment : BaseFragment(), CategoryClickListener {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainViewModel = ViewModelProvider(activity!!, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater)

        val categoriesViewModel =
            ViewModelProvider(this, viewModelFactory)[CategoriesViewModel::class.java]
        val categoriesAdapter = createCategoriesAdapter()

        initView(categoriesAdapter)
        subscribeCategories(categoriesViewModel, categoriesAdapter)

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        binding.scroll.scrollY = TOP_OF_SCROLL
    }

    override fun onCategoryClick(category: Category) {
        mainViewModel.selectCategory(category.id)
    }

    private fun initView(categoriesAdapter: CategoriesAdapter) {
        binding.categories.adapter = categoriesAdapter
        binding.btnBack.setOnClickListener { mainViewModel.movePageTo(FRAGMENT_NICKNAME) }
        binding.btnAppleBox.setOnClickListener { deployAppleBoxActivity() }
        initAdmob(binding.banner)
    }

    private fun createCategoriesAdapter(): CategoriesAdapter {
        return CategoriesAdapter().apply {
            setLifeCycleOwner(this@CategoriesFragment)
            setItemClickListener(this@CategoriesFragment)
        }
    }

    private fun subscribeCategories(viewModel: CategoriesViewModel, adapter: CategoriesAdapter) {
        viewModel.categories.observe(this, Observer {
            adapter.initCategories(it)
        })
    }

    private fun deployAppleBoxActivity() {
        val intent = Intent(activity, AppleBoxActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TOP_OF_SCROLL = 0

        private var INSTANCE: CategoriesFragment? = null
        fun getInstance() = INSTANCE
            ?: CategoriesFragment().apply { INSTANCE = this }
    }
}
