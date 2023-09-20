package com.udtt.applegamsung.ui.intro

import androidx.lifecycle.viewModelScope
import com.udtt.applegamsung.data.repository.UserIdentifyRepository
import com.udtt.applegamsung.domain.repository.TestResultsRepository
import com.udtt.applegamsung.util.BaseViewModel
import com.udtt.applegamsung.util.log
import kotlinx.coroutines.launch
import java.util.UUID

/**
 * Created By Yun Hyeok
 * on 3월 21, 2020
 */

class IntroViewModel(
    private val userIdentifyRepository: UserIdentifyRepository,
    private val testResultsRepository: TestResultsRepository,
) : BaseViewModel() {

    var isTestResultEmpty: Boolean = true
        private set

    init {
        loadTestResultIsEmpty()
    }

    fun loadTestResultIsEmpty() {
        viewModelScope.launch {
            testResultsRepository.getTestResults()
                .onSuccess { isTestResultEmpty = it.isEmpty() }
        }
    }

    fun checkDeviceId() {
        val deviceId = userIdentifyRepository.getDeviceId()
        if (deviceId == null) {
            saveDeviceId(createDeviceId())
        }
        log("device ID : $deviceId")
    }

    private fun createDeviceId(): String = UUID.randomUUID().toString()

    private fun saveDeviceId(deviceId: String) {
        userIdentifyRepository.saveDeviceId(deviceId)
        log("Device Id saved")
    }
}
