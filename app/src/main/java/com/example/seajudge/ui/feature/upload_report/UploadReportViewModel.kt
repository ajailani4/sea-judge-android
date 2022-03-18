package com.example.seajudge.ui.feature.upload_report

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UploadReportViewModel @Inject constructor(

) : ViewModel() {
    var cameraScreenVis by mutableStateOf(true)
    var backConfirmationDlgVis by mutableStateOf(false)
    var photo by mutableStateOf<File?>(null)
    var violation by mutableStateOf("")
    var location by mutableStateOf("")
    var date by mutableStateOf("")
    var time by mutableStateOf("")

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
}