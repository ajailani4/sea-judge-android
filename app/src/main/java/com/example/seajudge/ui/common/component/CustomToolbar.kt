package com.example.seajudge.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.seajudge.ui.theme.Primary
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ArrowBack

@Composable
fun CustomToolbar(
    title: String,
    contentColor: Color,
    backgroundColor: Color,
    onBackBtnClicked: () -> Unit
) {
    Box(modifier = Modifier
        .background(color = backgroundColor)
        .fillMaxWidth()
        .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onBackBtnClicked
            ) {
                Icon(
                    imageVector = EvaIcons.Fill.ArrowBack,
                    tint = contentColor,
                    contentDescription = "Back button"
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = contentColor,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h3
            )
        }
    }
}