package com.example.seajudge.ui.feature.login

sealed class LoginState {
    object Idle : LoginState()
    object LogginIn : LoginState()
    object Success : LoginState()
    data class Fail(val message: String?) : LoginState()
    data class Error(val message: String?) : LoginState()
}