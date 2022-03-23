package com.example.seajudge.ui.feature.upload_report.component

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.runtime.*
import com.example.seajudge.ui.feature.upload_report.event.CameraMenuEvent
import com.example.seajudge.util.convertInputStreamToFile
import com.example.seajudge.util.takePicture
import java.io.File

@Composable
fun CameraView(
    context: Context,
    onImageCaptured: (File) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            val inputStream = context.contentResolver.openInputStream(uri)

            if (inputStream != null) {
                onImageCaptured(context.convertInputStreamToFile(inputStream))
            }
        }
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

                is CameraMenuEvent.Capture -> {
                    imageCapture.takePicture(
                        context = context,
                        onImageCaptured = onImageCaptured,
                        onError = onError
                    )
                }

                is CameraMenuEvent.ViewGallery -> {
                    galleryLauncher.launch("image/*")
                }
            }
        }
    )
}