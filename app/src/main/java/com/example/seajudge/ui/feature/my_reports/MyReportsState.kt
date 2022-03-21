package com.example.seajudge.ui.feature.my_reports

import com.example.seajudge.data.model.Report

sealed class MyReportsState {
    object Idle : MyReportsState()
    object LoadingMyReports : MyReportsState()
    data class MyReports(val myReports: List<Report>?) : MyReportsState()
    data class Fail(val message: String?) : MyReportsState()
    data class Error(val message: String?) : MyReportsState()
}
