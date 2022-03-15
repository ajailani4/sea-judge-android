package com.example.seajudge.ui.feature.upload_report.screen

import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.seajudge.ui.common.component.CustomAlertDialog
import com.example.seajudge.ui.common.component.CustomToolbar
import com.example.seajudge.ui.feature.upload_report.UploadReportViewModel
import java.io.File

@Composable
fun UploadReportScreen(
    navController: NavController,
    uploadReportViewModel: UploadReportViewModel = hiltViewModel()
) {
    val cameraScreenVis = uploadReportViewModel.cameraScreenVis
    val onCameraScreenVisChanged = uploadReportViewModel::onCameraScreenVisChanged
    val backConfirmationDlgVis = uploadReportViewModel.backConfirmationDlgVis
    val onBackConfirmationDlgVisChanged = uploadReportViewModel::onBackConfirmationDlgVis
    val photo = uploadReportViewModel.photo
    val onPhotoChanged = uploadReportViewModel::onPhotoChanged

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
            UploadReportForm(photo)
            AnimatedVisibility(
                visible = cameraScreenVis,
                enter = expandVertically(expandFrom = Alignment.Bottom),
                exit = shrinkVertically()
            ) {
                CameraScreen(
                    onBackBtnClicked = { navController.navigateUp() },
                    onCameraScreenVisChanged = onCameraScreenVisChanged,
                    onImageCaptured = { photo ->
                        onPhotoChanged(photo)
                    }
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

@OptIn(ExperimentalCoilApi::class)
@Composable
fun UploadReportForm(photo: File?) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
    ) {
        if (photo != null) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(maxHeight = 400.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = rememberImagePainter(photo),
                contentScale = ContentScale.Crop,
                contentDescription = "Report image"
            )
        }
    }
}