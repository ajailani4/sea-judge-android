package com.example.seajudge.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.seajudge.ui.feature.dashboard.DashboardScreen
import com.example.seajudge.ui.feature.edit_report.EditReportScreen
import com.example.seajudge.ui.feature.login.LoginScreen
import com.example.seajudge.ui.feature.my_reports.MyReportsScreen
import com.example.seajudge.ui.feature.onboarding.OnboardingScreen
import com.example.seajudge.ui.feature.register.RegisterScreen
import com.example.seajudge.ui.feature.upload_report.screen.UploadReportScreen

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

        composable(route = Screen.UploadReportScreen.route) {
            UploadReportScreen(navController)
        }

        composable(
            route = Screen.EditReportScreen.route +
                    "?id={id}&violation={violation}&location={location}&date={date}&time={time}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                },
                navArgument("violation") {
                    type = NavType.StringType
                },
                navArgument("location") {
                    type = NavType.StringType
                },
                navArgument("date") {
                    type = NavType.StringType
                },
                navArgument("time") {
                    type = NavType.StringType
                }
            )
        ) {
            EditReportScreen(navController)
        }

        /** Bottom bar menu */
        composable(route = Screen.DashboardScreen.route) {
            DashboardScreen()
        }

        composable(route = Screen.MyReportsScreen.route) {
            MyReportsScreen(navController)
        }
    }
}