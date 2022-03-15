package com.example.seajudge.ui.feature.upload_report.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.seajudge.ui.common.component.CustomAlertDialog
import com.example.seajudge.ui.common.component.CustomToolbar
import com.example.seajudge.ui.feature.upload_report.UploadViewModel

@Composable
fun UploadReportScreen(
    navController: NavController,
    uploadViewModel: UploadViewModel = hiltViewModel()
) {
    val cameraScreenVis = uploadViewModel.cameraScreenVis
    val onCameraScreenVisChanged = uploadViewModel::onCameraScreenVisChanged
    val backConfirmationDlgVis = uploadViewModel.backConfirmationDlgVis
    val onBackConfirmationDlgVisChanged = uploadViewModel::onBackConfirmationDlgVis

    Scaffold(
        topBar = {
            if (!cameraScreenVis) {
                CustomToolbar(
                    title = "Unggah Laporan",
                    onBackBtnClicked = { onBackConfirmationDlgVisChanged(true) }
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            UploadReportForm()
            AnimatedVisibility(
                visible = cameraScreenVis,
                enter = expandVertically(expandFrom = Alignment.Bottom),
                exit = shrinkVertically()
            ) {
                CameraScreen(
                    onBackBtnClicked = { navController.navigateUp() },
                    onCameraScreenVisChanged = onCameraScreenVisChanged
                )
            }

            // Back confirmation dialog
            if (backConfirmationDlgVis) {
                CustomAlertDialog(
                    onVisibilityChanged = onBackConfirmationDlgVisChanged,
                    title = "Keluar dari halaman unggah",
                    message = "Apakah kamu yakin ingin membatalkan unggah laporan?",
                    onConfirmClicked = {
                        navController.navigateUp()
                        onBackConfirmationDlgVisChanged(false)
                    },
                    onDismissClicked = { onBackConfirmationDlgVisChanged(false) }
                )
            }
        }
    }
}

@Composable
fun UploadReportForm() {
    Text(text = "Form")
}