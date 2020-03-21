package com.udtt.applegamsung.data.repository

import android.content.Context

/**
 * Created By Yun Hyeok
 * on 3월 21, 2020
 */

class DeviceIdRepository(context: Context) {

    private val sharedPreference = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPreference.edit()

    fun setDeviceId(deviceId: String) {
        editor.putString(DEVICE_ID_KEY, deviceId).apply()
    }

    fun getDeviceId(): String? = sharedPreference.getString(DEVICE_ID_KEY, null)

    companion object {
        private const val FILE_NAME = "AppleGamsung"
        private const val DEVICE_ID_KEY = "deviceId"
    }
}