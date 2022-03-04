package com.example.seajudge.ui.feature.login

import com.example.seajudge.data.model.response.CredentialResponse

sealed class LoginEvent {
    object Idle : LoginEvent()
    object Submit : LoginEvent()
}
