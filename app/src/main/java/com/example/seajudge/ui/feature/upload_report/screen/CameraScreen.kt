package com.example.seajudge.ui.feature.upload_report.screen

import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.seajudge.ui.common.component.CustomToolbar
import com.example.seajudge.ui.feature.upload_report.component.CameraView
import com.example.seajudge.ui.theme.Primary

@Composable
fun CameraScreen(
    onBackBtnClicked: () -> Unit
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
                Log.d("PhotoFile", file.toString())
            },
            onError = { exception ->

            }
        )
    }
}