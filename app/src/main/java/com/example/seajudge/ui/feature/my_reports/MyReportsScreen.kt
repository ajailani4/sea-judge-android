package com.example.seajudge.ui.feature.my_reports

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.seajudge.R
import com.example.seajudge.ui.Screen
import com.example.seajudge.ui.common.component.*
import com.example.seajudge.ui.theme.Primary
import com.example.seajudge.ui.theme.Red
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.LogOut
import kotlinx.coroutines.launch

@Composable
fun MyReportsScreen(
    navController: NavController,
    myReportsViewModel: MyReportsViewModel = hiltViewModel()
) {
    val onEvent = myReportsViewModel::onEvent
    val myReportsState = myReportsViewModel.myReportsState
    val deleteReportState = myReportsViewModel.deleteReportState
    val logoutState = myReportsViewModel.logoutState
    val swipeRefreshing = myReportsViewModel.swipeRefreshing
    val onSwipeRefreshingChanged = myReportsViewModel::onSwipeRefreshingChanged
    val selectedReportImg = myReportsViewModel.selectedReportImg
    val onSelectedReportImgChanged = myReportsViewModel::onSelectedReportImgChanged
    val fullSizeImgVis = myReportsViewModel.fullSizeImgVis
    val onFulLSizeImgVisChanged = myReportsViewModel::onFulLSizeImgVisChanged
    val onDeletedReportChanged = myReportsViewModel::onDeletedReportChanged
    val deletedReportDlgVis = myReportsViewModel.deletedReportDlgVis
    val onDeletedReportDlgVisChanged = myReportsViewModel::onDeletedReportDlgVisChanged
    val logoutConfirmDlgVis = myReportsViewModel.logoutConfirmDlgVis
    val onLogoutConfirmDlgVisChanged = myReportsViewModel::onLogoutConfirmDlgVisChanged

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = swipeRefreshing),
            onRefresh = {
                onSwipeRefreshingChanged(false)
                onEvent(MyReportsEvent.LoadMyReports)
            },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    contentColor = Primary
                )
            }
        ) {
            Box {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    item {
                        MyReportsHeader(onLogoutConfirmDlgVisChanged)
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    // Observe my reports state
                    when (myReportsState) {
                        is MyReportsState.LoadingMyReports -> {
                            item {
                                MediumProgressBar()
                            }
                        }

                        is MyReportsState.MyReports -> {
                            val myReports = myReportsState.myReports

                            if (myReports != null) {
                                if (myReports.isNotEmpty()) {
                                    items(myReports) { myReport ->
                                        ReportCard(
                                            report = myReport,
                                            isEditable = true,
                                            onImageClicked = {
                                                onSelectedReportImgChanged(myReport.photo)
                                                onFulLSizeImgVisChanged(true)
                                            },
                                            onEditBtnClicked = {
                                                navController.navigate(
                                                    Screen.EditReportScreen.route +
                                                        "?id=${myReport.id}&violation=${myReport.violation}&location=${myReport.location}&date=${myReport.date}&time=${myReport.time}"
                                                )
                                            },
                                            onDeleteBtnClicked = {
                                                onDeletedReportChanged(myReport.id)
                                                onDeletedReportDlgVisChanged(true)
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

                        is MyReportsState.FailMyReports -> {
                            coroutineScope.launch {
                                myReportsState.message?.let { message ->
                                    scaffoldState.snackbarHostState.showSnackbar(message)
                                }
                            }
                        }

                        is MyReportsState.ErrorMyReports -> {
                            coroutineScope.launch {
                                myReportsState.message?.let { message ->
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

                // Deleted report confirmation dialog
                if (deletedReportDlgVis) {
                    CustomAlertDialog(
                        onVisibilityChanged = onDeletedReportDlgVisChanged,
                        title = "Hapus Laporan",
                        message = "Apakah kamu yakin ingin menghapus laporan ini?",
                        onConfirmClicked = {
                            onDeletedReportDlgVisChanged(false)
                            onEvent(MyReportsEvent.DeleteReport)
                        },
                        onDismissClicked = {
                            onDeletedReportDlgVisChanged(false)
                        }
                    )
                }

                // Logout confirmation dialog
                if (logoutConfirmDlgVis) {
                    CustomAlertDialog(
                        onVisibilityChanged = onLogoutConfirmDlgVisChanged,
                        title = "Logout",
                        message = "Apakah kamu yakin ingin logout?",
                        onConfirmClicked = {
                            onLogoutConfirmDlgVisChanged(false)
                            onEvent(MyReportsEvent.Logout)
                        },
                        onDismissClicked = { onLogoutConfirmDlgVisChanged(false) }
                    )
                }
            }
        }

        // Observe delete report state
        when (deleteReportState) {
            is MyReportsState.DeletingReport -> {
                FullSizeProgressBar()
            }

            is MyReportsState.SuccessDeleteReport -> {
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Laporan berhasil dihapus")
                    }

                    onEvent(MyReportsEvent.LoadMyReports)
                    onEvent(MyReportsEvent.Idle)
                }
            }

            is MyReportsState.FailDeleteReport -> {
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        deleteReportState.message?.let { message ->
                            scaffoldState.snackbarHostState.showSnackbar(message)
                        }
                    }
                }
            }

            is MyReportsState.ErrorDeleteReport -> {
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        deleteReportState.message?.let { message ->
                            scaffoldState.snackbarHostState.showSnackbar(message)
                        }
                    }
                }
            }

            else -> {}
        }

        // Observe logout state
        when (logoutState) {
            is MyReportsState.SuccessLogout -> {
                navController.navigate(Screen.OnboardingScreen.route) {
                    launchSingleTop = true

                    popUpTo(Screen.DashboardScreen.route) {
                        inclusive = true
                    }
                }
            }

            else -> {}
        }
    }
}

@Composable
fun MyReportsHeader(onLogoutConfirmDlgVisChanged: (Boolean) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
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
            IconButton(onClick = { onLogoutConfirmDlgVisChanged(true) }) {
                Icon(
                    modifier = Modifier.sizeIn(27.dp),
                    imageVector = EvaIcons.Fill.LogOut,
                    tint = Red,
                    contentDescription = "Logout icon"
                )
            }
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