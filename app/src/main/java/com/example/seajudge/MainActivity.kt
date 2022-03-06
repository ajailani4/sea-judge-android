package com.example.seajudge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.seajudge.ui.BottomBar
import com.example.seajudge.ui.Navigation
import com.example.seajudge.ui.Screen
import com.example.seajudge.ui.feature.splash.SplashViewModel
import com.example.seajudge.ui.theme.Primary
import com.example.seajudge.ui.theme.SeaJudgeTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Plus
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val hasNoBottomMenuRoutes = listOf(
        Screen.OnboardingScreen.route,
        Screen.LoginScreen.route,
        Screen.RegisterScreen.route
    )

    Scaffold(
        bottomBar = {
            if (!hasNoBottomMenuRoutes.contains(currentRoute)) {
                BottomBar(navController)
            }
        },
        floatingActionButton = {
            if (!hasNoBottomMenuRoutes.contains(currentRoute)) {
                FloatingActionButton(
                    shape = CircleShape,
                    backgroundColor = Primary,
                    contentColor = Color.White,
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = EvaIcons.Fill.Plus,
                        contentDescription = "Upload report icon"
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Box(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())) {
            Navigation(navController = navController, startDestination = startDestination)
        }
    }
}