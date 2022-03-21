package com.example.seajudge.ui.feature.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.seajudge.ui.feature.onboarding.OnboardingItem
import com.example.seajudge.ui.theme.BlackGrey
import com.example.seajudge.ui.theme.Primary

@Composable
fun OnboardingItemScreen(item: OnboardingItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Image(
            modifier = Modifier
                .align(CenterHorizontally)
                .size(480.dp),
            painter = painterResource(id = item.image),
            contentDescription = "Illustration"
        )
        Text(
            text = item.title,
            color = Primary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = item.description,
            color = BlackGrey,
            style = MaterialTheme.typography.subtitle1
        )
    }
}
