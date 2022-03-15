package com.example.seajudge.ui.feature.upload_report.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.seajudge.ui.feature.upload_report.screen.CameraScreen

@Composable
fun UploadReportScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        CameraScreen(onBackBtnClicked = { navController.navigateUp() })
    }
}