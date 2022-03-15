package com.example.seajudge.ui.feature.upload_report

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(

) : ViewModel() {
    var cameraScreenVis by mutableStateOf(true)
    var backConfirmationDlgVis by mutableStateOf(false)

    fun onCameraScreenVisChanged(visibility: Boolean) {
        cameraScreenVis = visibility
    }

    fun onBackConfirmationDlgVis(visibility: Boolean) {
        backConfirmationDlgVis = visibility
    }
}