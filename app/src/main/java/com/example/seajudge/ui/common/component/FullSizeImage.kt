package com.example.seajudge.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun FullSizeImage(
    image: String,
    onVisibilityChanged: (Boolean) -> Unit
) {
    Dialog(onDismissRequest = { onVisibilityChanged(false) }) {
        Image(
            painter = rememberImagePainter(image),
            contentDescription = "Full size image"
        )
    }
}