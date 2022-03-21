package com.example.seajudge.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.seajudge.R
import com.example.seajudge.ui.theme.Primary

@Composable
fun EmptyItemIllustration() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(200.dp, 216.dp),
            painter = painterResource(id = R.drawable.img_empty_item),
            contentDescription = "Empty item illustration"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Belum ada laporan",
            color = Primary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h3
        )
    }
}