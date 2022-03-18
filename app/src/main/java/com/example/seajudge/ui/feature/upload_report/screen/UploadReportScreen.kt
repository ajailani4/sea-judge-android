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
import com.example.seajudge.ui.common.component.CustomAlertDialog
import com.example.seajudge.ui.common.component.CustomToolbar
import com.example.seajudge.ui.feature.login.LoginEvent
import com.example.seajudge.ui.feature.login.LoginState
import com.example.seajudge.ui.feature.upload_report.UploadReportViewModel
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
import java.io.File

@Composable
fun UploadReportScreen(
    navController: NavController,
    uploadReportViewModel: UploadReportViewModel = hiltViewModel()
) {
    val cameraScreenVis = uploadReportViewModel.cameraScreenVis
    val onCameraScreenVisChanged = uploadReportViewModel::onCameraScreenVisChanged
    val backConfirmationDlgVis = uploadReportViewModel.backConfirmationDlgVis
    val onBackConfirmationDlgVisChanged = uploadReportViewModel::onBackConfirmationDlgVis
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

    val context = LocalContext.current
    (context as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

    Scaffold(
        topBar = {
            if (!cameraScreenVis) {
                CustomToolbar(
                    title = "Unggah Laporan",
                    onBackBtnClicked = { onBackConfirmationDlgVisChanged(true) }
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            UploadReportForm(
                context = context,
                photo = photo,
                violation = violation,
                onViolationChanged = onViolationChanged,
                location = location,
                onLocationChanged = onLocationChanged,
                date = date,
                onDateChanged = onDateChanged,
                time = time,
                onTimeChanged = onTimeChanged
            )
            AnimatedVisibility(
                visible = cameraScreenVis,
                enter = expandVertically(expandFrom = Alignment.Bottom),
                exit = shrinkVertically()
            ) {
                CameraScreen(
                    onBackBtnClicked = { navController.navigateUp() },
                    onCameraScreenVisChanged = onCameraScreenVisChanged,
                    onImageCaptured = { photo ->
                        onPhotoChanged(photo)
                    }
                )
            }

            BackHandler {
                onBackConfirmationDlgVisChanged(true)
            }
            
            // Back confirmation dialog
            if (backConfirmationDlgVis) {
                CustomAlertDialog(
                    onVisibilityChanged = onBackConfirmationDlgVisChanged,
                    title = "Keluar dari halaman unggah",
                    message = "Apakah kamu yakin ingin membatalkan unggah laporan?",
                    onConfirmClicked = {
                        navController.navigateUp()
                        onBackConfirmationDlgVisChanged(false)
                    },
                    onDismissClicked = { onBackConfirmationDlgVisChanged(false) }
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun UploadReportForm(
    context: Context,
    photo: File?,
    violation: String,
    onViolationChanged: (String) -> Unit,
    location: String,
    onLocationChanged: (String) -> Unit,
    date: String,
    onDateChanged: (String) -> Unit,
    time: String,
    onTimeChanged: (String) -> Unit
) {
    Column(modifier = Modifier
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
                Spacer(modifier = Modifier.width(20.dp))
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
                enabled = true,
                onClick = {}
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