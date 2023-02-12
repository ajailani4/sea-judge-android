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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.seajudge.ui.BottomBar
import com.example.seajudge.ui.Navigation
import com.example.seajudge.ui.Screen
import com.example.seajudge.ui.feature.splash.SplashViewModel
import com.example.seajudge.ui.theme.Primary
import com.example.seajudge.ui.theme.SeaJudgeTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Content(startDestination: String) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val hasBottomMenuRoutes = listOf(
        Screen.DashboardScreen.route,
        Screen.MyReportsScreen.route
    )

    val cameraPermissionState =
        rememberPermissionState(android.Manifest.permission.CAMERA)
    val readExStoragePermissionState =
        rememberPermissionState(android.Manifest.permission.READ_EXTERNAL_STORAGE)

    Scaffold(
        bottomBar = {
            if (hasBottomMenuRoutes.contains(currentRoute)) {
                BottomBar(navController)
            }
        },
        floatingActionButton = {
            if (hasBottomMenuRoutes.contains(currentRoute)) {
                FloatingActionButton(
                    shape = CircleShape,
                    backgroundColor = Primary,
                    contentColor = Color.White,
                    onClick = {
                        when {
                            cameraPermissionState.hasPermission && readExStoragePermissionState.hasPermission -> {
                                navController.navigate(Screen.UploadReportScreen.route)
                            }

                            cameraPermissionState.shouldShowRationale ||
                                    !cameraPermissionState.permissionRequested -> {
                                cameraPermissionState.launchPermissionRequest()
                            }

                            readExStoragePermissionState.shouldShowRationale ||
                                    !readExStoragePermissionState.permissionRequested -> {
                                readExStoragePermissionState.launchPermissionRequest()
                            }
                        }
                    }
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