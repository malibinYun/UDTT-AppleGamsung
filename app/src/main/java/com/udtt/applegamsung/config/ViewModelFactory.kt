package com.udtt.applegamsung.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.data.repository.*
import com.udtt.applegamsung.ui.applebox.AppleBoxViewModel
import com.udtt.applegamsung.ui.applepower.ApplePowerViewModel
import com.udtt.applegamsung.ui.intro.IntroViewModel
import com.udtt.applegamsung.ui.main.MainViewModel
import com.udtt.applegamsung.ui.main.categories.CategoriesViewModel
import com.udtt.applegamsung.ui.main.nickname.NicknameViewModel
import com.udtt.applegamsung.ui.main.products.ProductsViewModel

class ViewModelFactory(
    private val userIdentifyRepository: UserIdentifyRepository,
    private val categoriesRepository: CategoriesRepository,
    private val productsRepository: ProductsRepository,
    private val appleBoxItemsRepository: AppleBoxItemsRepository,
    private val testResultsRepository: TestResultsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {

            MainViewModel::class.java ->
                modelClass.getConstructor(APPLE_BOX_REPO).newInstance(appleBoxItemsRepository)

            NicknameViewModel::class.java ->
                modelClass.getConstructor(USER_ID_REPO).newInstance(userIdentifyRepository)

            CategoriesViewModel::class.java ->
                modelClass.getConstructor(CATEGORIES_REPO).newInstance(categoriesRepository)

            ProductsViewModel::class.java ->
                modelClass.getConstructor(PRODUCTS_REPO).newInstance(productsRepository)

            IntroViewModel::class.java ->
                modelClass.getConstructor(USER_ID_REPO, TEST_RESULT_REPO)
                    .newInstance(userIdentifyRepository, testResultsRepository)

            AppleBoxViewModel::class.java ->
                modelClass.getConstructor(USER_ID_REPO, APPLE_BOX_REPO)
                    .newInstance(userIdentifyRepository, appleBoxItemsRepository)

            ApplePowerViewModel::class.java ->
                modelClass.getConstructor(USER_ID_REPO, APPLE_BOX_REPO, TEST_RESULT_REPO)
                    .newInstance(
                        userIdentifyRepository,
                        appleBoxItemsRepository,
                        testResultsRepository
                    )

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
    }

    companion object {
        private val USER_ID_REPO = UserIdentifyRepository::class.java
        private val CATEGORIES_REPO = CategoriesRepository::class.java
        private val PRODUCTS_REPO = ProductsRepository::class.java
        private val APPLE_BOX_REPO = AppleBoxItemsRepository::class.java
        private val TEST_RESULT_REPO = TestResultsRepository::class.java
    }
}
