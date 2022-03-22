package com.example.seajudge.ui.feature.my_reports

import com.example.seajudge.data.model.Report

sealed class MyReportsState {
    object Idle : MyReportsState()

    object LoadingMyReports : MyReportsState()
    object DeletingReport : MyReportsState()

    data class MyReports(val myReports: List<Report>?) : MyReportsState()
    object SuccessDeleteReport : MyReportsState()

    data class FailMyReports(val message: String?) : MyReportsState()
    data class FailDeleteReport(val message: String?) : MyReportsState()

    data class ErrorMyReports(val message: String?) : MyReportsState()
    data class ErrorDeleteReport(val message: String?) : MyReportsState()
}
