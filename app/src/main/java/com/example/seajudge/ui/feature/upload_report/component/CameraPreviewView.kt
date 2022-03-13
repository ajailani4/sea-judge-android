package com.example.seajudge.ui.feature.upload_report.component

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.seajudge.ui.feature.upload_report.event.CameraMenuEvent
import com.example.seajudge.util.getCameraProvider
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Camera
import compose.icons.evaicons.outline.Flip2
import compose.icons.evaicons.outline.Image

@Composable
fun CameraPreviewView(
    context: Context,
    imageCapture: ImageCapture,
    lensFacing: Int = CameraSelector.LENS_FACING_BACK,
    onCameraMenuEvent: (CameraMenuEvent) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()
    val previewView = remember { PreviewView(context) }

    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView(
            factory = { previewView },
            modifier = Modifier
                .fillMaxWidth()
                .weight(9f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.weight(1f)) {
            CameraMenu(onCameraMenuEvent)
        }
    }
}

@Composable
fun CameraMenu(onCameraMenuEvent: (CameraMenuEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier.size(60.dp),
            onClick = { onCameraMenuEvent(CameraMenuEvent.SwitchLens) }
        ) {
            Icon(
                imageVector = EvaIcons.Outline.Flip2,
                tint = Color.Black,
                contentDescription = "Switch camera icon"
            )
        }
        Button(
            modifier = Modifier.size(60.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = { onCameraMenuEvent(CameraMenuEvent.Capture) }
        ) {
            Icon(
                imageVector = EvaIcons.Outline.Camera,
                contentDescription = "Camera icon"
            )
        }
        IconButton(
            modifier = Modifier.size(60.dp),
            onClick = { onCameraMenuEvent(CameraMenuEvent.ViewGallery) }
        ) {
            Icon(
                imageVector = EvaIcons.Outline.Image,
                tint = Color.Black,
                contentDescription = "Gallery icon"
            )
        }
    }
}