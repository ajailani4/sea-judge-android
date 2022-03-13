package com.example.seajudge.ui.feature.upload_report.screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.seajudge.ui.common.component.CustomToolbar
import com.example.seajudge.ui.feature.upload_report.component.CameraView
import com.example.seajudge.ui.theme.Primary

@Composable
fun CameraScreen(
    onBackBtnClicked: () -> Unit
) {
    Scaffold(topBar = {
        CustomToolbar(
            title = "Foto Pelanggaran",
            contentColor = Primary,
            backgroundColor = Color.White,
            onBackBtnClicked = onBackBtnClicked
        )
    }) {
        CameraView(
            onImageCaptured = { uri, isFromGallery ->
                // handle the result
            },
            onError = { exception ->

            }
        )
    }
}