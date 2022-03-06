package com.example.seajudge.ui.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.seajudge.ui.theme.BackgroundGrey
import com.example.seajudge.ui.theme.Grey
import com.example.seajudge.ui.theme.Primary

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .background(color = BackgroundGrey)
            .fillMaxSize()
    ) {
        Text(text = "Dashboard")
    }
}