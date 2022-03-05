package com.example.seajudge.ui.feature.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.seajudge.R
import com.example.seajudge.ui.Screen
import com.example.seajudge.ui.feature.onboarding.component.OnboardingItemScreen
import com.example.seajudge.ui.theme.Primary
import com.example.seajudge.ui.theme.Secondary
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val onboardingItems = listOf(
        OnboardingItem.OnboardingScreenOne,
        OnboardingItem.OnboardingScreenTwo,
        OnboardingItem.OnboardingScreenThree
    )
    val pagerState = rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        AnimatedVisibility(visible = pagerState.currentPage == onboardingItems.size - 1) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(45.dp),
                        painter = painterResource(id = R.drawable.logo_app),
                        contentDescription = "App logo"
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Sea Judge",
                        color = Primary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h1
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Selamat Datang",
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h2
                )
            }
        }
        
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            count = onboardingItems.size,
            state = pagerState
        ) { index ->
            OnboardingItemScreen(item = onboardingItems[index])
        }
        
        // Login and register button
        AnimatedVisibility(visible = pagerState.currentPage == onboardingItems.size - 1) {
            Column {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
                    onClick = { navController.navigate(Screen.LoginScreen.route) }
                ) {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Masuk",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.h3
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Secondary),
                    onClick = { navController.navigate(Screen.RegisterScreen.route) }
                ) {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Daftar",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.h3
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column(modifier = Modifier.align(CenterEnd)) {
                AnimatedVisibility(visible = pagerState.currentPage != onboardingItems.size - 1) {
                    TextButton(
                        onClick = {
                            scope.launch {
                                pagerState.scrollToPage(onboardingItems.size - 1)
                            }
                        }
                    ) {
                        Text(
                            text = "Skip",
                            color = Secondary,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.h3
                        )
                    }
                }
            }
            HorizontalPagerIndicator(
                modifier = Modifier.align(Center),
                pagerState = pagerState,
                activeColor = Primary,
                inactiveColor = Color(0xFFC4C4C4)
            )
        }
    }
}