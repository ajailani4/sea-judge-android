package com.example.seajudge.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
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

        items.map { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.icon!!,
                        contentDescription = screen.title
                    )
                },
                label = {
                    Text(
                        text = screen.title!!,
                        fontFamily = poppinsFamily
                    )
                },
                selected = currentRoute == screen.route,
                selectedContentColor = Primary,
                unselectedContentColor = Grey,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}