package com.example.seajudge.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import coil.compose.rememberImagePainter

@Composable
fun FullSizeImage(
    image: String,
    onFullSizeImageDlgVis: (Boolean) -> Unit
) {
    Dialog(onDismissRequest = { onFullSizeImageDlgVis(false) }) {
        Image(
            painter = rememberImagePainter(image),
            contentDescription = "Full size image"
        )
    }
}