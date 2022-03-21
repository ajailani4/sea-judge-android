package com.example.seajudge.ui.feature.edit_report

import android.app.Activity
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.seajudge.ui.Screen
import com.example.seajudge.ui.common.component.CustomAlertDialog
import com.example.seajudge.ui.common.component.CustomToolbar
import com.example.seajudge.ui.common.component.FullSizeProgressBar
import com.example.seajudge.ui.feature.upload_report.UploadReportState
import com.example.seajudge.ui.feature.upload_report.event.UploadReportEvent
import com.example.seajudge.ui.theme.DarkGrey
import com.example.seajudge.ui.theme.Primary
import com.example.seajudge.ui.theme.poppinsFamily
import com.example.seajudge.util.Formatter
import com.example.seajudge.util.showDatePicker
import com.example.seajudge.util.showTimePicker
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Calendar
import compose.icons.evaicons.outline.Clock
import kotlinx.coroutines.launch

@Composable
fun EditReportScreen(
    navController: NavController,
    editReportViewModel: EditReportViewModel = hiltViewModel()
) {
    val onEvent = editReportViewModel::onEvent
    val editReportState = editReportViewModel.editReportState
    val backConfirmDlgVis = editReportViewModel.backConfirmDlgVis
    val onBackConfirmDlgVisChanged = editReportViewModel::onBackConfirmDlgVisChanged
    val violation = editReportViewModel.violation
    val onViolationChanged = editReportViewModel::onViolationChanged
    val location = editReportViewModel.location
    val onLocationChanged = editReportViewModel::onLocationChanged
    val date = editReportViewModel.date
    val onDateChanged = editReportViewModel::onDateChanged
    val time = editReportViewModel.time
    val onTimeChanged = editReportViewModel::onTimeChanged
    
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    
    val context = LocalContext.current
    (context as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomToolbar(
                title = "Edit Laporan",
                onBackBtnClicked = { onBackConfirmDlgVisChanged(true) }
            )
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Pelanggaran",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body1
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = violation,
                    onValueChange = {
                        if (it.length in 0..250) onViolationChanged(it)
                    },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = poppinsFamily,
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = "${violation.length}/250",
                    color = DarkGrey,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Lokasi Pelanggaran",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body1
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = location,
                    onValueChange = {
                        if (it.length in 0..100) onLocationChanged(it)
                    },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = poppinsFamily,
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = "${location.length}/100",
                    color = DarkGrey,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Tanggal dan Waktu Pelanggaran",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body1
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        modifier = Modifier
                            .weight(3f)
                            .clickable(onClick = {
                                context.showDatePicker(onDateChanged)
                            }),
                        value = if (date != "") Formatter.formatDate(date) else "",
                        onValueChange = {},
                        trailingIcon = {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = EvaIcons.Outline.Calendar,
                                contentDescription = "Date icon"
                            )
                        },
                        enabled = false,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontFamily = poppinsFamily,
                            fontSize = 14.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(22.dp))
                    TextField(
                        modifier = Modifier
                            .weight(2f)
                            .clickable(onClick = {
                                context.showTimePicker(onTimeChanged)
                            }),
                        value = time,
                        onValueChange = {},
                        trailingIcon = {
                            Icon(
                                modifier = Modifier.size(22.dp),
                                imageVector = EvaIcons.Outline.Clock,
                                contentDescription = "Time icon"
                            )
                        },
                        enabled = false,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontFamily = poppinsFamily,
                            fontSize = 14.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
                    enabled = editReportState != EditReportState.EditingReport,
                    onClick = {
                        if (violation.isNotEmpty() && location.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()) {
                            onEvent(EditReportEvent.EditReport)
                        } else {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Isi form dengan lengkap!")
                            }
                        }
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Edit",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }

            BackHandler {
                onBackConfirmDlgVisChanged(true)
            }

            // Back confirmation dialog
            if (backConfirmDlgVis) {
                CustomAlertDialog(
                    onVisibilityChanged = onBackConfirmDlgVisChanged,
                    title = "Keluar dari halaman edit",
                    message = "Apakah kamu yakin ingin membatalkan edit laporan?",
                    onConfirmClicked = {
                        navController.navigateUp()
                        onBackConfirmDlgVisChanged(false)
                    },
                    onDismissClicked = { onBackConfirmDlgVisChanged(false) }
                )
            }
        }

        // Observe edit report state
        when (editReportState) {
            is EditReportState.Idle -> {}

            is EditReportState.EditingReport -> {
                FullSizeProgressBar()
            }

            is EditReportState.SuccessEditReport -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.DashboardScreen.route) {
                        popUpTo(Screen.DashboardScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }

            is EditReportState.FailEditReport -> {
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        editReportState.message?.let { message ->
                            scaffoldState.snackbarHostState.showSnackbar(message)
                        }
                    }
                }
            }

            is EditReportState.ErrorEditReport -> {
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        editReportState.message?.let { message ->
                            scaffoldState.snackbarHostState.showSnackbar(message)
                        }
                    }
                }
            }
        }
    }
}