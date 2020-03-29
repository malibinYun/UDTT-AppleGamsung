package com.udtt.applegamsung.ui.applepower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.data.entity.Category
import com.udtt.applegamsung.data.repository.AppleBoxItemsRepository
import com.udtt.applegamsung.util.BaseViewModel
import com.udtt.applegamsung.util.log

class ApplePowerViewModel(
    private val appleBoxItemsRepository: AppleBoxItemsRepository
) : BaseViewModel() {

    private val _appleBoxItems = MutableLiveData<List<AppleBoxItem>>()
    val appleBoxItems: LiveData<List<AppleBoxItem>>
        get() = _appleBoxItems

    private val _havingCategories = MutableLiveData<List<Category.Type>>()
    val havingCategories: LiveData<List<Category.Type>>
        get() = _havingCategories

    private val _score = MutableLiveData<Double>()
    val score: LiveData<Double>
        get() = _score

    init {
        loadAppleBoxItems()
    }

    private fun loadAppleBoxItems() {
        appleBoxItemsRepository.getAppleBoxItems {
            _appleBoxItems.value = it
            _havingCategories.value = extractCategoryTypes(it)
            _score.value = getScore()

            log("_appleBoxItems.value : ${_appleBoxItems.value}")
            log("_havingCategories.value : ${_havingCategories.value}")
            log("getBonusRatio() : ${getBonusRatio()}")
            log("_score.value : ${_score.value}")
        }
    }

    private fun extractCategoryTypes(items: List<AppleBoxItem>): List<Category.Type> {
        return items.map { it.product.categoryType }.distinct()
    }

    private fun getScore(): Double {
        val bonusRatio = getBonusRatio()
        val productTotalScore = calculateAppleBoxItemsScore()
        return productTotalScore * bonusRatio
    }

    private fun getBonusRatio(): Double {
        val baseRatio = 0.1
        val numberOfCategories = _havingCategories.value?.size
            ?: throw IllegalStateException("havingCategories는 null일 수 없음.")
        return 1.0 + baseRatio * numberOfCategories
    }

    private fun calculateAppleBoxItemsScore(): Double {
        val currentAppleBoxItems = getCurrentAppleBoxItems()
        return currentAppleBoxItems.map { it.getScore() }.sum()
    }

    private fun getCurrentAppleBoxItems(): List<AppleBoxItem> {
        return _appleBoxItems.value
            ?: throw IllegalStateException("appleBoxItems는 null일 수 없음.")
    }
}