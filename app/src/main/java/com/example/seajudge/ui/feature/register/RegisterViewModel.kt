package com.example.seajudge.ui.feature.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(

) : ViewModel() {
    var username by mutableStateOf("")
    var name by mutableStateOf("")
    var state by mutableStateOf("")
    var job by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordVisibility by mutableStateOf(false)

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
}