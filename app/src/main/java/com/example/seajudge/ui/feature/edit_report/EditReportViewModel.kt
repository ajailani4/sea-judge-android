package com.example.seajudge.ui.feature.edit_report

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class EditReportViewModel @Inject constructor(

) : ViewModel() {
    var backConfirmDlgVis by mutableStateOf(false)
    var violation by mutableStateOf("")
    var location by mutableStateOf("")
    var date by mutableStateOf("")
    var time by mutableStateOf("")

    fun onBackConfirmDlgVisChanged(visibility: Boolean) {
        backConfirmDlgVis = visibility
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