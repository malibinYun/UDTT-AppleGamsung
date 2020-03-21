package com.udtt.applegamsung.ui.intro

import com.udtt.applegamsung.data.repository.DeviceIdRepository
import com.udtt.applegamsung.util.BaseViewModel
import com.udtt.applegamsung.util.log
import java.util.*

/**
 * Created By Yun Hyeok
 * on 3월 21, 2020
 */

class IntroViewModel(
    private val deviceIdRepository: DeviceIdRepository
) : BaseViewModel() {

    fun checkDeviceId() {
        val deviceId = deviceIdRepository.getDeviceId()
        if (deviceId == null) {
            saveDeviceId(createDeviceId())
        }
        log("device ID : $deviceId")
    }

    private fun createDeviceId(): String = UUID.randomUUID().toString()

    private fun saveDeviceId(deviceId: String) {
        deviceIdRepository.setDeviceId(deviceId)
        log("Device Id saved")
    }
}