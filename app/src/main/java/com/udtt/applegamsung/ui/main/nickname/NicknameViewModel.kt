package com.udtt.applegamsung.ui.main.nickname

import androidx.lifecycle.MutableLiveData
import com.udtt.applegamsung.data.repository.UserIdentifyRepository
import com.udtt.applegamsung.util.BaseViewModel

class NicknameViewModel(
    private val userIdentifyRepository: UserIdentifyRepository
) : BaseViewModel() {

    val nickname = MutableLiveData<String>()

    fun saveNickname() {
        val nickname = this.nickname.value ?: throw IllegalArgumentException("닉네임은 null일 수 없음.")
        userIdentifyRepository.saveNickname(nickname)
    }
}