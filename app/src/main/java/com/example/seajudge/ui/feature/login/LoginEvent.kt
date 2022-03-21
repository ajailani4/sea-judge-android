package com.example.seajudge.ui.feature.login

sealed class LoginEvent {
    object Idle : LoginEvent()
    object Submit : LoginEvent()
}
