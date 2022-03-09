package com.example.seajudge.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.seajudge.ui.theme.Grey
import com.example.seajudge.ui.theme.Primary
import com.example.seajudge.ui.theme.poppinsFamily

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Screen.DashboardScreen,
        Screen.MyReportsScreen
    )

    BottomNavigation(
        elevation = 20.dp,
        backgroundColor = Color.White
    ) {
        val navBarStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBarStackEntry?.destination?.route

        items.map {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = it.icon!!,
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(
                        text = it.title!!,
                        fontFamily = poppinsFamily
                    )
                },
                selected = currentRoute == it.route,
                selectedContentColor = Primary,
                unselectedContentColor = Grey,
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(Screen.DashboardScreen.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}