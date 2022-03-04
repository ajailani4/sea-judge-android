package com.example.seajudge.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.seajudge.ui.feature.login.LoginScreen
import com.example.seajudge.ui.feature.onboarding.OnboardingScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun Navigation(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.OnboardingScreen.route) {
            OnboardingScreen(navController)
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
    }
}