package com.example.seajudge.ui.feature.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.seajudge.R
import com.example.seajudge.data.model.Report
import com.example.seajudge.ui.feature.dashboard.component.ReportCard
import com.example.seajudge.ui.theme.*
import com.google.accompanist.insets.navigationBarsWithImePadding
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.LogOut
import compose.icons.evaicons.fill.Search

@Composable
fun DashboardScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            DashboardHeader()
            SearchTextField()
            DashboardContentSection()
        }
    }
}

@Composable
fun DashboardHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
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
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier.sizeIn(27.dp),
                imageVector = EvaIcons.Fill.LogOut,
                tint = Red,
                contentDescription = "Logout icon"
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField() {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .navigationBarsWithImePadding(),
        value = "",
        onValueChange = {},
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = SearchTextFieldGrey,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                imageVector = EvaIcons.Fill.Search,
                tint = Grey,
                contentDescription = "Search icon"
            )
        },
        placeholder = {
            Text(
                text = "Cari Laporan",
                color = Grey,
                style = MaterialTheme.typography.body1
            )
        },
        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black,
            fontFamily = poppinsFamily,
            fontSize = 14.sp
        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            keyboardController?.hide()
        })
    )
}

@Composable
fun DashboardContentSection() {
    Column(modifier = Modifier.padding(20.dp)) {
        ReportCard(
            Report(
                id = 1,
                username = "g_zayvich",
                reporter = "George Zayvich",
                image = "https://images.unsplash.com/photo-1559128010-7c1ad6e1b6a5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8.jpg",
                violation = "Pembuangan minyak sembarangan",
                location = "Selat Sunda",
                date = "2022-02-26",
                time = "08:25"
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        ReportCard(
            Report(
                id = 1,
                username = "g_zayvich",
                reporter = "Floyd Zayvich",
                image = "https://images.unsplash.com/photo-1570651851409-93d5add773d7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8.jpg",
                violation = "Pembuangan minyak sembarangan",
                location = "Selat Sunda",
                date = "2022-02-26",
                time = "08:25"
            )
        )
    }
}