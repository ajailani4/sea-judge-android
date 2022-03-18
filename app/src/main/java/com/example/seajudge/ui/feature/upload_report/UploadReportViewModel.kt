package com.example.seajudge.ui.feature.upload_report

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seajudge.domain.use_case.report.UploadReportUseCase
import com.example.seajudge.domain.use_case.user_credential.GetUsernameUseCase
import com.example.seajudge.ui.feature.upload_report.event.UploadReportEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UploadReportViewModel @Inject constructor(
    private val getUsernameUseCase: GetUsernameUseCase,
    private val uploadReportUseCase: UploadReportUseCase
) : ViewModel() {
    var uploadReportState by mutableStateOf<UploadReportState>(UploadReportState.Idle)
    var cameraScreenVis by mutableStateOf(true)
    var backConfirmationDlgVis by mutableStateOf(false)
    var photo by mutableStateOf<File?>(null)
    var violation by mutableStateOf("")
    var location by mutableStateOf("")
    var date by mutableStateOf("")
    var time by mutableStateOf("")

    fun onEvent(event: UploadReportEvent) {
        when (event) {
            UploadReportEvent.UploadReport -> uploadReport()
        }
    }

    fun onCameraScreenVisChanged(visibility: Boolean) {
        cameraScreenVis = visibility
    }

    fun onBackConfirmationDlgVis(visibility: Boolean) {
        backConfirmationDlgVis = visibility
    }

    fun onPhotoChanged(file: File?) {
        photo = file
    }

    fun onViolationChanged(text: String) {
        violation = text
    }

    fun onLocationChanged(text: String) {
        location = text
    }

    fun onDateChanged(text: String) {
        date = text
    }

    fun onTimeChanged(text: String) {
        time = text
    }

    private fun uploadReport() {
        viewModelScope.launch {
            uploadReportState = UploadReportState.UploadingReport

            uploadReportState = try {
                val response = uploadReportUseCase.invoke(
                    username = getUsernameUseCase.invoke()!!,
                    photo = photo!!,
                    violation = violation,
                    location = location,
                    date = date,
                    time = time
                )

                if (response.code() == 201) {
                    UploadReportState.SuccessUploadReport
                } else {
                    UploadReportState.FailUploadReport("Terjadi kesalahan")
                }
            } catch (e: Exception) {
                UploadReportState.ErrorUploadReport("Error")
            }
        }
    }
}