package com.example.seajudge.ui.feature.dashboard

import com.example.seajudge.data.model.Report

sealed class DashboardState {
    object Idle : DashboardState()

    object LoadingReports : DashboardState()

    data class Reports(val reports: List<Report>?) : DashboardState()
    object SuccessLogout : DashboardState()

    data class FailReports(val message: String?) : DashboardState()

    data class ErrorReports(val message: String?) : DashboardState()
}
