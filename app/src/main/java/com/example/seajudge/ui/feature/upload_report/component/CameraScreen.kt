package com.example.seajudge.ui.feature.upload_report.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.seajudge.ui.theme.Grey
import com.example.seajudge.ui.theme.Primary
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.ArrowBack
import compose.icons.evaicons.outline.Camera

@Composable
fun CameraScreen(
    onBackBtnClicked: () -> Unit
) {
    Scaffold(topBar = { CameraScreenToolbar(onBackBtnClicked) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // CameraView()
            Box(modifier = Modifier
                .background(color = Grey)
                .fillMaxWidth()
                .weight(9f),
            ) // Temporary
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.weight(1f)) {
                Button(
                    modifier = Modifier.size(62.dp),
                    shape = RoundedCornerShape(20.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = EvaIcons.Outline.Camera,
                        contentDescription = "Camera icon"
                    )
                }
            }
        }
    }
}


@Composable
fun CameraScreenToolbar(onBackBtnClicked: () -> Unit) {
    Box(modifier = Modifier
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
                    tint = Primary,
                    contentDescription = "Back button"
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Foto Pelanggaran",
                color = Primary,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h3
            )
        }
    }
}