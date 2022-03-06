package com.example.seajudge.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.seajudge.ui.feature.dashboard.DashboardScreen
import com.example.seajudge.ui.feature.login.LoginScreen
import com.example.seajudge.ui.feature.onboarding.OnboardingScreen
import com.example.seajudge.ui.feature.register.RegisterScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun Navigation(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.OnboardingScreen.route) {
            OnboardingScreen(navController)
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController)
        }

        // Bottom bar menu
        composable(route = Screen.DashboardScreen.route) {
            DashboardScreen()
        }

        composable(route = Screen.MyReportsScreen.route) {
            // MyReportsScreen()
        }
    }
}