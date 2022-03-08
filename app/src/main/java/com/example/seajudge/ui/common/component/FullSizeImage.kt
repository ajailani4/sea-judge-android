package com.example.seajudge.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.seajudge.ui.theme.Red
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Close

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FullSizeImage(
    image: String,
    onVisibilityChanged: (Boolean) -> Unit
) {
    Dialog(
        onDismissRequest = { onVisibilityChanged(false) },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(color = Color.Black) {
            Column {
                IconButton(
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End),
                    onClick = { onVisibilityChanged(false) }
                ) {
                    Icon(
                        imageVector = EvaIcons.Fill.Close,
                        tint = Color.White,
                        contentDescription = "Close full size image icon"
                    )
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = image,
                            builder = {
                                size(OriginalSize)
                            }
                        ),
                        contentScale = ContentScale.Fit,
                        contentDescription = "Full size image"
                    )
                }
            }
        }
    }
}