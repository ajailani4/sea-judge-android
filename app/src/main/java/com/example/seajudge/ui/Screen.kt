package com.example.seajudge.ui

sealed class Screen(val route: String) {
    object OnboardingScreen : Screen("onboarding_screen")
}
