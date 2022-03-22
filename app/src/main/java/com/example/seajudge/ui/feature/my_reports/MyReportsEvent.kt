package com.example.seajudge.ui.feature.my_reports

sealed class MyReportsEvent {
    object Idle : MyReportsEvent()
    object LoadMyReports : MyReportsEvent()
    object DeleteReport : MyReportsEvent()
    object Logout : MyReportsEvent()
}
