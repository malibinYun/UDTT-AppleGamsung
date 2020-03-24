package com.udtt.applegamsung.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.data.repository.CategoriesRepository
import com.udtt.applegamsung.data.repository.DeviceIdRepository
import com.udtt.applegamsung.data.repository.ProductsRepository
import com.udtt.applegamsung.ui.intro.IntroViewModel
import com.udtt.applegamsung.ui.main.MainViewModel
import com.udtt.applegamsung.ui.main.categories.CategoriesViewModel
import com.udtt.applegamsung.ui.main.nickname.NicknameViewModel
import com.udtt.applegamsung.ui.main.products.ProductsViewModel

class ViewModelFactory(
    private val deviceIdRepository: DeviceIdRepository,
    private val categoriesRepository: CategoriesRepository,
    private val productsRepository: ProductsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java,
            NicknameViewModel::class.java ->
                modelClass.getConstructor().newInstance()

            IntroViewModel::class.java ->
                modelClass.getConstructor(DEVICE_ID_REPO).newInstance(deviceIdRepository)

            CategoriesViewModel::class.java ->
                modelClass.getConstructor(CATEGORIES_REPO).newInstance(categoriesRepository)

            ProductsViewModel::class.java ->
                modelClass.getConstructor(PRODUCTS_REPO).newInstance(productsRepository)

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
    }

    companion object {
        private val DEVICE_ID_REPO = DeviceIdRepository::class.java
        private val CATEGORIES_REPO = CategoriesRepository::class.java
        private val PRODUCTS_REPO = ProductsRepository::class.java
    }
}