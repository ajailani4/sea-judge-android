package com.example.seajudge.ui.feature.upload_report.screen

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.seajudge.ui.Screen
import com.example.seajudge.ui.common.component.CustomAlertDialog
import com.example.seajudge.ui.common.component.CustomToolbar
import com.example.seajudge.ui.common.component.FullSizeProgressBar
import com.example.seajudge.ui.feature.upload_report.UploadReportState
import com.example.seajudge.ui.feature.upload_report.UploadReportViewModel
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
import id.zelory.compressor.Compressor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun UploadReportScreen(
    navController: NavController,
    uploadReportViewModel: UploadReportViewModel = hiltViewModel()
) {
    val onEvent = uploadReportViewModel::onEvent
    val uploadReportState = uploadReportViewModel.uploadReportState
    val cameraScreenVis = uploadReportViewModel.cameraScreenVis
    val onCameraScreenVisChanged = uploadReportViewModel::onCameraScreenVisChanged
    val backConfirmDlgVis = uploadReportViewModel.backConfirmDlgVis
    val onBackConfirmDlgVisChanged = uploadReportViewModel::onBackConfirmDlgVisChanged
    val photo = uploadReportViewModel.photo
    val onPhotoChanged = uploadReportViewModel::onPhotoChanged
    val violation = uploadReportViewModel.violation
    val onViolationChanged = uploadReportViewModel::onViolationChanged
    val location = uploadReportViewModel.location
    val onLocationChanged = uploadReportViewModel::onLocationChanged
    val date = uploadReportViewModel.date
    val onDateChanged = uploadReportViewModel::onDateChanged
    val time = uploadReportViewModel.time
    val onTimeChanged = uploadReportViewModel::onTimeChanged

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val context = LocalContext.current
    (context as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (!cameraScreenVis) {
                CustomToolbar(
                    title = "Upload Laporan",
                    onBackBtnClicked = { onBackConfirmDlgVisChanged(true) }
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            UploadReportForm(
                onEvent = onEvent,
                uploadReportState = uploadReportState,
                context = context,
                photo = photo,
                violation = violation,
                onViolationChanged = onViolationChanged,
                location = location,
                onLocationChanged = onLocationChanged,
                date = date,
                onDateChanged = onDateChanged,
                time = time,
                onTimeChanged = onTimeChanged,
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState
            )
            AnimatedVisibility(
                visible = cameraScreenVis,
                enter = expandVertically(expandFrom = Alignment.Bottom),
                exit = shrinkVertically()
            ) {
                CameraScreen(
                    onBackBtnClicked = { onBackConfirmDlgVisChanged(true) },
                    onCameraScreenVisChanged = onCameraScreenVisChanged,
                    onImageCaptured = { photo ->
                        coroutineScope.launch {
                            onPhotoChanged(Compressor.compress(context, photo))
                        }
                    }
                )
            }

            BackHandler {
                onBackConfirmDlgVisChanged(true)
            }

            // Back confirmation dialog
            if (backConfirmDlgVis) {
                CustomAlertDialog(
                    onVisibilityChanged = onBackConfirmDlgVisChanged,
                    title = "Keluar dari halaman unggah",
                    message = "Apakah kamu yakin ingin membatalkan unggah laporan?",
                    onConfirmClicked = {
                        navController.navigateUp()
                        onBackConfirmDlgVisChanged(false)
                    },
                    onDismissClicked = { onBackConfirmDlgVisChanged(false) }
                )
            }
        }

        // Observe upload report state
        when (uploadReportState) {
            is UploadReportState.Idle -> {}

            is UploadReportState.UploadingReport -> {
                FullSizeProgressBar()
            }

            is UploadReportState.SuccessUploadReport -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.DashboardScreen.route) {
                        popUpTo(Screen.DashboardScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }

            is UploadReportState.FailUploadReport -> {
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        uploadReportState.message?.let { message ->
                            scaffoldState.snackbarHostState.showSnackbar(message)
                        }
                    }
                }
            }

            is UploadReportState.ErrorUploadReport -> {
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        uploadReportState.message?.let { message ->
                            scaffoldState.snackbarHostState.showSnackbar(message)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun UploadReportForm(
    onEvent: (UploadReportEvent) -> Unit,
    uploadReportState: UploadReportState,
    context: Context,
    photo: File?,
    violation: String,
    onViolationChanged: (String) -> Unit,
    location: String,
    onLocationChanged: (String) -> Unit,
    date: String,
    onDateChanged: (String) -> Unit,
    time: String,
    onTimeChanged: (String) -> Unit,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            if (photo != null) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .sizeIn(maxHeight = 400.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    painter = rememberImagePainter(photo),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Report image"
                )
            }

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
                modifier = Modifier.align(End),
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
                modifier = Modifier.align(End),
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
                enabled = uploadReportState != UploadReportState.UploadingReport,
                onClick = {
                    if (photo != null && violation.isNotEmpty() && location.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()) {
                        onEvent(UploadReportEvent.UploadReport)
                    } else {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Isi form dengan lengkap!")
                        }
                    }
                }
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Upload",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}