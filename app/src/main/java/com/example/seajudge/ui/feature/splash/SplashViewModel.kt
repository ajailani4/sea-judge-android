package com.example.seajudge.ui.feature.splash

import androidx.lifecycle.ViewModel
import com.example.seajudge.domain.use_case.user_credential.GetAccessTokenUseCase
import com.example.seajudge.domain.use_case.user_credential.GetUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUsernameUseCase: GetUsernameUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : ViewModel() {
    fun getUsername() = getUsernameUseCase.invoke()
    fun getAccessToken() = getAccessTokenUseCase.invoke()
}