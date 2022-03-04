package com.example.seajudge.ui.feature.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seajudge.data.model.request.LoginRequest
import com.example.seajudge.domain.use_case.user.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {
    var loginState by mutableStateOf<LoginState>(LoginState.Idle)
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordVisibility by mutableStateOf(false)

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.Idle -> idle()

            LoginEvent.Submit -> login()
        }
    }

    fun onUsernameChanged(text: String) {
        username = text
    }

    fun onPasswordChanged(text: String) {
        password = text
    }

    fun onPasswordVisibilityChanged() {
        passwordVisibility = !passwordVisibility
    }

    private fun idle() {
        loginState = LoginState.Idle
    }

    private fun login() {
        viewModelScope.launch {
            loginState = LoginState.LogginIn

            loginState = try {
                val response = loginUserUseCase.invoke(
                    LoginRequest(username = username, password = password)
                )

                when (response.code()) {
                    201 -> {
                        LoginState.Success
                    }

                    401 -> {
                        LoginState.Fail("Username atau password salah")
                    }

                    else -> {
                        LoginState.Fail("Terjadi kesalahan")
                    }
                }
            } catch (e: Exception) {
                LoginState.Error("Error")
            }
        }
    }
}