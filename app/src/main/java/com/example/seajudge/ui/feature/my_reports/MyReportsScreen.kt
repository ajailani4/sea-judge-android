package com.example.seajudge.ui.feature.my_reports

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.seajudge.R
import com.example.seajudge.ui.theme.Primary
import com.example.seajudge.ui.theme.Red
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.LogOut

@Composable
fun MyReportsScreen(
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        Box {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    MyReportsHeader()
                }
            }
        }
    }
}

@Composable
fun MyReportsHeader() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "App logo"
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Sea Judge",
                color = Primary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h2
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Laporanku",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h3
        )
    }
}