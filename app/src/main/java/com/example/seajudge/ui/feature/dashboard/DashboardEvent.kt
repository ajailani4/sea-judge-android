package com.example.seajudge.ui.feature.dashboard

sealed class DashboardEvent {
    object LoadReports : DashboardEvent()
}