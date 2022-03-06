package com.example.seajudge.ui

import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Home
import compose.icons.evaicons.fill.List

sealed class Screen(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object OnboardingScreen : Screen("onboarding_screen")

    object LoginScreen : Screen("login_screen")

    object RegisterScreen : Screen("register_screen")

    object DashboardScreen : Screen(
        route = "dashboard_screen",
        title = "Dashboard",
        icon = EvaIcons.Fill.Home
    )

    object MyReportsScreen : Screen(
        route = "my_reports_screen",
        title = "My Reports",
        icon = EvaIcons.Fill.List
    )
}
