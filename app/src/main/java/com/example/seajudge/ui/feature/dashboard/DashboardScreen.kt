package com.example.seajudge.ui.feature.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.seajudge.R
import com.example.seajudge.ui.common.component.FullSizeImage
import com.example.seajudge.ui.common.component.MediumProgressBar
import com.example.seajudge.ui.common.component.ReportCard
import com.example.seajudge.ui.theme.*
import com.google.accompanist.insets.navigationBarsWithImePadding
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.LogOut
import compose.icons.evaicons.fill.Search
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(
    navController: NavController,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {
    val onEvent = dashboardViewModel::onEvent
    val reportsState = dashboardViewModel.reportsState
    val selectedReportImg = dashboardViewModel.selectedReportImg
    val onSelectedReportImgChanged = dashboardViewModel::onSelectedReportImgChanged
    val fullSizeImgVis = dashboardViewModel.fullSizeImgVis
    val onFulLSizeImgVisChanged = dashboardViewModel::onFulLSizeImgVisChanged
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                DashboardHeader()
                Spacer(modifier = Modifier.height(15.dp))
                SearchTextField()
                Spacer(modifier = Modifier.height(30.dp))
            }

            // Full size image
            if (fullSizeImgVis) {
                item {
                    FullSizeImage(
                        image = selectedReportImg,
                        onVisibilityChanged = onFulLSizeImgVisChanged
                    )
                }
            }

            // Observe reports state
            when (reportsState) {
                is DashboardState.LoadingReports -> {
                    item {
                        MediumProgressBar()
                    }
                }

                is DashboardState.Reports -> {
                    val reports = reportsState.reports

                    if (reports != null) {
                        if (reports.isNotEmpty()) {
                            items(reports) { report ->
                                ReportCard(
                                    report = report,
                                    onClick = {
                                        onSelectedReportImgChanged(report.image)
                                        onFulLSizeImgVisChanged(true)
                                    }
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                        }
                    }
                }

                is DashboardState.FailReports -> {
                    scope.launch {
                        reportsState.message?.let { message ->
                            scaffoldState.snackbarHostState.showSnackbar(message)
                        }
                    }
                }

                is DashboardState.ErrorReports -> {
                    scope.launch {
                        reportsState.message?.let { message ->
                            scaffoldState.snackbarHostState.showSnackbar(message)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
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