package com.example.seajudge.ui.feature.upload_report.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.seajudge.ui.feature.upload_report.event.CameraMenuEvent

@Composable
fun CameraView(
    onImageCaptured: (Uri, Boolean) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    val context = LocalContext.current
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) onImageCaptured(uri, true)
    }

    CameraPreviewView(
        context = context,
        imageCapture = imageCapture,
        lensFacing = lensFacing,
        onCameraMenuEvent = { cameraMenuEvent ->
            when (cameraMenuEvent) {
                is CameraMenuEvent.SwitchLens -> {
                    lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK) {
                        CameraSelector.LENS_FACING_FRONT
                    } else {
                        CameraSelector.LENS_FACING_BACK
                    }
                }

                is CameraMenuEvent.Capture -> {}

                is CameraMenuEvent.ViewGallery -> {}
            }
        }
    )
}