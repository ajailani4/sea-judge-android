package com.example.seajudge.ui

sealed class Screen(val route: String) {
    object OnboardingScreen : Screen("onboarding_screen")

    object LoginScreen : Screen("login_screen")

    object RegisterScreen : Screen("register_screen")

    object DashboardScreen : Screen("dashboard_screen")
}
