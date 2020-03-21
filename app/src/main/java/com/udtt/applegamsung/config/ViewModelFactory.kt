package com.udtt.applegamsung.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.data.repository.CategoriesRepository
import com.udtt.applegamsung.data.repository.DeviceIdRepository
import com.udtt.applegamsung.ui.categories.CategoriesViewModel
import com.udtt.applegamsung.ui.intro.IntroViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val deviceIdRepository: DeviceIdRepository,
    private val categoriesRepository: CategoriesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {

            IntroViewModel::class.java ->
                modelClass.getConstructor(DEVICE_ID_REPO).newInstance(deviceIdRepository)

            CategoriesViewModel::class.java ->
                modelClass.getConstructor(CATEGORIES_REPO).newInstance(categoriesRepository)

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
    }

    companion object {
        private val DEVICE_ID_REPO = DeviceIdRepository::class.java
        private val CATEGORIES_REPO = CategoriesRepository::class.java
    }
}