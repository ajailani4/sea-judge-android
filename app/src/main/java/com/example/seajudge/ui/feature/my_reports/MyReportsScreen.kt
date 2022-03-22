package com.example.seajudge.ui.feature.my_reports

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
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
import kotlinx.coroutines.launch

@Composable
fun MyReportsScreen(
    navController: NavController,
    myReportsViewModel: MyReportsViewModel = hiltViewModel()
) {
    val onEvent = myReportsViewModel::onEvent
    val myReportsState = myReportsViewModel.myReportsState
    val deleteReportState = myReportsViewModel.deleteReportState
    val selectedReportImg = myReportsViewModel.selectedReportImg
    val onSelectedReportImgChanged = myReportsViewModel::onSelectedReportImgChanged
    val fullSizeImgVis = myReportsViewModel.fullSizeImgVis
    val onFulLSizeImgVisChanged = myReportsViewModel::onFulLSizeImgVisChanged
    val onDeletedReportChanged = myReportsViewModel::onDeletedReportChanged
    val deletedReportDlgVis = myReportsViewModel.deletedReportDlgVis
    val onDeletedReportDlgVisChanged = myReportsViewModel::onDeletedReportDlgVisChanged

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        Box {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    MyReportsHeader()
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