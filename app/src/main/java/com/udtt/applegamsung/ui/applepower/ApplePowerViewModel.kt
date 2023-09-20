package com.udtt.applegamsung.ui.applepower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udtt.applegamsung.data.entity.AppleBoxItem
import com.udtt.applegamsung.domain.model.testresult.applepower.ApplePower
import com.udtt.applegamsung.domain.model.category.Category
import com.udtt.applegamsung.domain.model.testresult.TestResult
import com.udtt.applegamsung.data.repository.UserIdentifyRepository
import com.udtt.applegamsung.domain.repository.AppleBoxItemsRepository
import com.udtt.applegamsung.domain.repository.TestResultsRepository
import com.udtt.applegamsung.util.BaseViewModel
import kotlinx.coroutines.launch

class ApplePowerViewModel(
    userIdentifyRepository: UserIdentifyRepository,
    private val appleBoxItemsRepository: AppleBoxItemsRepository,
    private val testResultsRepository: TestResultsRepository,
) : BaseViewModel() {

    val userNickName = userIdentifyRepository.getNickname() ?: throw ILLEGAL_STATE_EXCEPTION
    private val deviceId = userIdentifyRepository.getDeviceId() ?: throw ILLEGAL_STATE_EXCEPTION
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
        viewModelScope.launch {
            _isLoading.value = true

            appleBoxItemsRepository.getAppleBoxItems()
                .onSuccess {
                    appleBoxItems.addAll(it)
                    _havingProducts.value = extractProductNames(it)
                    _havingCategories.value = extractCategoryTypes(it)
                    loadApplePower(getScore())

                    checkSavedTestResultOrSave()
                }
            _isLoading.value = false
        }
    }

    private fun loadApplePower(score: Double) {
        viewModelScope.launch {
            _score.value = score.toInt()

            testResultsRepository.getApplePower(score.toInt())
                .onSuccess { _applePower.value = it }
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

    fun deleteTestResultAndAppleBox() {
        viewModelScope.launch {
            appleBoxItemsRepository.removeAllAppleBoxItems()
            testResultsRepository.removeAllTestResults()
        }
    }

    private fun checkSavedTestResultOrSave() {
        viewModelScope.launch {
            testResultsRepository.getTestResults()
                .onSuccess { if (it.isEmpty()) saveTestResult() }
        }
    }

    private fun createTestResult(): TestResult {
        val currentScore = _score.value ?: throw ILLEGAL_STATE_EXCEPTION
        val productList = _havingProducts.value ?: throw ILLEGAL_STATE_EXCEPTION
        return TestResult(
            deviceId,
            userNickName,
            currentScore
        ).apply { this.productList = productList }
    }

    private fun saveTestResult() {
        val testResult = createTestResult()
        viewModelScope.launch {
            testResultsRepository.saveTestResult(testResult)
        }
    }

    companion object {
        private const val BASE_RATIO = 0.9
        private const val ADDITIONAL_RATIO = 0.1

        private val ILLEGAL_STATE_EXCEPTION = IllegalStateException("존재할 수 없는 상태")
    }
}
