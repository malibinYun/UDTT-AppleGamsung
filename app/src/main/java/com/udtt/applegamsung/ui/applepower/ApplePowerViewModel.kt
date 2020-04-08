package com.udtt.applegamsung.ui.applepower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.entity.ApplePower
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.repository.AppleBoxItemsRepository
import com.udtt.applegamsung.data.repository.TestResultsRepository
import com.udtt.applegamsung.data.repository.UserIdentifyRepository
import com.udtt.applegamsung.util.BaseViewModel
import com.udtt.applegamsung.util.log

class ApplePowerViewModel(
    userIdentifyRepository: UserIdentifyRepository,
    private val appleBoxItemsRepository: AppleBoxItemsRepository,
    private val testResultsRepository: TestResultsRepository
) : BaseViewModel() {

    val userNickName = userIdentifyRepository.getNickname()

    private val appleBoxItems: MutableList<AppleBoxItem> = mutableListOf()

    private val _havingProducts = MutableLiveData<List<String>>()
    val havingProducts: LiveData<List<String>>
        get() = _havingProducts

    private val _havingCategories = MutableLiveData<List<Category.Type>>()
    val havingCategories: LiveData<List<Category.Type>>
        get() = _havingCategories

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _applePower = MutableLiveData<ApplePower>()
    val applePower: LiveData<ApplePower>
        get() = _applePower

    init {
        loadAppleBoxItems()
    }

    private fun loadAppleBoxItems() {
        appleBoxItemsRepository.getAppleBoxItems {
            appleBoxItems.addAll(it)
            _havingProducts.value = extractProductNames(it)
            _havingCategories.value = extractCategoryTypes(it)
            loadApplePower(getScore())

            log("_appleBoxItems.value : $appleBoxItems")
            log("_havingCategories.value : ${_havingCategories.value}")
            log("getBonusRatio() : ${getBonusRatio()}")
            log("_score.value : ${_score.value}")
        }
    }

    private fun loadApplePower(score: Double) {
        _score.value = score.toInt()
        testResultsRepository.getApplePower(score.toInt()) {
            _applePower.value = it
        }
    }

    private fun extractCategoryTypes(items: List<AppleBoxItem>): List<Category.Type> {
        return items.map { it.product.categoryType }.distinct()
    }

    private fun extractProductNames(items: List<AppleBoxItem>): List<String> {
        return items.map { it.product.name }
    }

    private fun getScore(): Double {
        val bonusRatio = getBonusRatio()
        val productTotalScore = calculateAppleBoxItemsScore()
        return productTotalScore * bonusRatio
    }

    private fun getBonusRatio(): Double {
        val numberOfCategories = _havingCategories.value?.size
            ?: throw IllegalStateException("havingCategories는 null일 수 없음.")
        return BASE_RATIO + ADDITIONAL_RATIO * numberOfCategories
    }

    private fun calculateAppleBoxItemsScore(): Double {
        return appleBoxItems.map { it.getScore() }.sum()
    }

    companion object {
        private const val BASE_RATIO = 0.9
        private const val ADDITIONAL_RATIO = 0.1
    }
}