package com.example.seajudge.ui.feature.upload_report

sealed class UploadReportState {
    object Idle : UploadReportState()
    object UploadingReport : UploadReportState()
    object SuccessUploadReport : UploadReportState()
    data class FailUploadReport(val message: String?) : UploadReportState()
    data class ErrorUploadReport(val message: String?) : UploadReportState()
}
