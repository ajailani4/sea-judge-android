package com.example.seajudge.ui.feature.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seajudge.data.model.request.RegisterRequest
import com.example.seajudge.domain.use_case.user.RegisterUserUseCase
import com.example.seajudge.domain.use_case.user_credential.SaveAccessTokenUseCase
import com.example.seajudge.domain.use_case.user_credential.SaveUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val saveUsernameUseCase: SaveUsernameUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase
) : ViewModel() {
    var registerState by mutableStateOf<RegisterState>(RegisterState.Idle)
    var username by mutableStateOf("")
    var name by mutableStateOf("")
    var state by mutableStateOf("")
    var job by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordVisibility by mutableStateOf(false)

    fun onEvent(event: RegisterEvent) {
        when (event) {
            RegisterEvent.Idle -> idle()

            RegisterEvent.Submit -> register()
        }
    }

    fun onUsernameChanged(text: String) {
        username = text
    }

    fun onNameChanged(text: String) {
        name = text
    }

    fun onStateChanged(text: String) {
        state = text
    }

    fun onJobChanged(text: String) {
        job = text
    }

    fun onPhoneNumberChanged(text: String) {
        phoneNumber = text
    }

    fun onPasswordChanged(text: String) {
        password = text
    }

    fun onPasswordVisibilityChanged() {
        passwordVisibility = !passwordVisibility
    }

    private fun idle() {
        registerState = RegisterState.Idle
    }

    private fun register() {
        registerState = RegisterState.Registering

        viewModelScope.launch {
            registerState = try {
                val response = registerUserUseCase.invoke(
                    RegisterRequest(
                        username = username,
                        password = password,
                        name = name,
                        state = state,
                        job = job,
                        phoneNumber = phoneNumber
                    )
                )

                when (response.code()) {
                    201 -> {
                        val credentialResponse = response.body()?.data
                        saveUsernameUseCase.invoke(credentialResponse?.username!!)
                        saveAccessTokenUseCase.invoke(credentialResponse.accessToken)

                        RegisterState.Success
                    }

                    409 -> {
                        RegisterState.Fail("Username sudah ada. Coba yang lain")
                    }

                    else -> {
                        RegisterState.Fail("Terjadi kesalahan")
                    }
                }
            } catch (e: Exception) {
                RegisterState.Error("Error")
            }
        }
    }
}