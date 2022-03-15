package com.example.seajudge.ui.common.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.seajudge.ui.theme.Primary

@Composable
fun CustomAlertDialog(
    onVisibilityChanged: (Boolean) -> Unit,
    title: String,
    message: String,
    onConfirmClicked: () -> Unit,
    onDismissClicked: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onVisibilityChanged(false) },
        title = {
            Text(
                text = title,
                color = Primary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h3
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.subtitle1
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirmClicked) {
                Text(
                    text = "Iya",
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body1
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissClicked) {
                Text(
                    text = "Tidak",
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    )
}