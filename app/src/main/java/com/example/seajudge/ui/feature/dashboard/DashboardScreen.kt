package com.example.seajudge.ui.feature.dashboard

import android.app.Activity
import android.view.WindowManager
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.seajudge.R
import com.example.seajudge.ui.common.component.EmptyItemIllustration
import com.example.seajudge.ui.common.component.FullSizeImage
import com.example.seajudge.ui.common.component.MediumProgressBar
import com.example.seajudge.ui.common.component.ReportCard
import com.example.seajudge.ui.theme.Grey
import com.example.seajudge.ui.theme.Primary
import com.example.seajudge.ui.theme.SearchTextFieldGrey
import com.example.seajudge.ui.theme.poppinsFamily
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Search
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DashboardScreen(dashboardViewModel: DashboardViewModel = hiltViewModel()) {
    val onEvent = dashboardViewModel::onEvent
    val reportsState = dashboardViewModel.reportsState
    val swipeRefreshing = dashboardViewModel.swipeRefreshing
    val onSwipeRefreshingChanged = dashboardViewModel::onSwipeRefreshingChanged
    val searchQuery = dashboardViewModel.searchQuery
    val onSearchQueryChanged = dashboardViewModel::onSearchQueryChanged
    val selectedReportImg = dashboardViewModel.selectedReportImg
    val onSelectedReportImgChanged = dashboardViewModel::onSelectedReportImgChanged
    val fullSizeImgVis = dashboardViewModel.fullSizeImgVis
    val onFulLSizeImgVisChanged = dashboardViewModel::onFulLSizeImgVisChanged

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val activity = LocalContext.current as Activity
    activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

    val keyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current

    Scaffold(scaffoldState = scaffoldState) { innerPadding ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = swipeRefreshing),
            onRefresh = {
                onSwipeRefreshingChanged(true)
                onEvent(DashboardEvent.LoadReports)
            },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    contentColor = Primary
                )
            }
        ) {
            Box(modifier = Modifier.padding(innerPadding)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    item {
                        DashboardHeader()
                        Spacer(modifier = Modifier.height(15.dp))
                        SearchTextField(
                            onEvent = onEvent,
                            searchQuery = searchQuery,
                            onSearchQueryChanged = onSearchQueryChanged,
                            keyboardController = keyboardController,
                            localFocusManager = localFocusManager
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                    }

                    // Observe reports state
                    when (reportsState) {
                        is DashboardState.LoadingReports -> {
                            item {
                                MediumProgressBar()
                            }
                        }

                        is DashboardState.Reports -> {
                            onSwipeRefreshingChanged(false)

                            val reports = reportsState.reports

                            if (reports != null) {
                                if (reports.isNotEmpty()) {
                                    items(reports) { report ->
                                        ReportCard(
                                            report = report,
                                            onImageClicked = {
                                                onSelectedReportImgChanged(report.photo)
                                                onFulLSizeImgVisChanged(true)
                                            }
                                        )
                                        Spacer(modifier = Modifier.height(20.dp))
                                    }
                                } else {
                                    item {
                                        EmptyItemIllustration()
                                    }
                                }
                            }
                        }

                        is DashboardState.FailReports -> {
                            onSwipeRefreshingChanged(false)

                            coroutineScope.launch {
                                reportsState.message?.let { message ->
                                    scaffoldState.snackbarHostState.showSnackbar(message)
                                }
                            }
                        }

                        is DashboardState.ErrorReports -> {
                            onSwipeRefreshingChanged(false)

                            coroutineScope.launch {
                                reportsState.message?.let { message ->
                                    scaffoldState.snackbarHostState.showSnackbar(message)
                                }
                            }
                        }

                        else -> {}
                    }
                }

                // Full size image
                if (fullSizeImgVis) {
                    FullSizeImage(
                        image = selectedReportImg,
                        onVisibilityChanged = onFulLSizeImgVisChanged
                    )
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
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    onEvent: (DashboardEvent) -> Unit,
    searchQuery: String?,
    onSearchQueryChanged: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?,
    localFocusManager: FocusManager
) {


    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchQuery ?: "",
        onValueChange = onSearchQueryChanged,
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
            localFocusManager.clearFocus()
            keyboardController?.hide()
            onEvent(DashboardEvent.LoadReports)
        })
    )
}