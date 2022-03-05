package com.example.seajudge.ui.feature.register

sealed class RegisterEvent {
    object Idle : RegisterEvent()
    object Submit : RegisterEvent()
}
