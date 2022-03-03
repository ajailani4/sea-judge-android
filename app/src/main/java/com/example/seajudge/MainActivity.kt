package com.example.seajudge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.seajudge.ui.Navigation
import com.example.seajudge.ui.Screen
import com.example.seajudge.ui.theme.SeaJudgeTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val startDestination = Screen.OnboardingScreen.route

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
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Content(startDestination: String) {
    val navController = rememberNavController()
    Navigation(navController = navController, startDestination = startDestination)
}