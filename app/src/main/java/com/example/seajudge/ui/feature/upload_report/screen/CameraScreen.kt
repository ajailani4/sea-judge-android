package com.example.seajudge.ui.feature.upload_report.screen

import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.seajudge.ui.common.component.CustomToolbar
import com.example.seajudge.ui.feature.upload_report.component.CameraView
import com.example.seajudge.ui.theme.Primary
import java.io.File

@Composable
fun CameraScreen(
    onBackBtnClicked: () -> Unit,
    onCameraScreenVisChanged: (Boolean) -> Unit,
    onImageCaptured: (File) -> Unit
) {
    val context = LocalContext.current

    Scaffold(topBar = {
        CustomToolbar(
            title = "Foto Pelanggaran",
            contentColor = Primary,
            backgroundColor = Color.White,
            onBackBtnClicked = onBackBtnClicked
        )
    }) {
        CameraView(
            context = context,
            onImageCaptured = { file ->
                onImageCaptured(file)
                onCameraScreenVisChanged(false)
                Log.d("PhotoFile", file.toString())
            },
            onError = { exception ->

            }
        )
    }
}