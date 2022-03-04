package com.example.seajudge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.seajudge.ui.Navigation
import com.example.seajudge.ui.Screen
import com.example.seajudge.ui.feature.splash.SplashViewModel
import com.example.seajudge.ui.theme.SeaJudgeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val startDestination =
            if (splashViewModel.getUsername() != "" && splashViewModel.getAccessToken() != "") {
                Screen.DashboardScreen.route
            } else {
                Screen.OnboardingScreen.route
            }

        setContent {
            App {
                Content(startDestination)
            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit) {
    SeaJudgeTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun Content(startDestination: String) {
    val navController = rememberNavController()
    Navigation(navController = navController, startDestination = startDestination)
}