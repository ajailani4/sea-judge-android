package com.example.seajudge.ui.feature.edit_report

sealed class EditReportState {
    object Idle : EditReportState()
    object EditingReport : EditReportState()
    object SuccessEditReport : EditReportState()
    data class FailEditReport(val message: String?) : EditReportState()
    data class ErrorEditReport(val message: String?) : EditReportState()
}
