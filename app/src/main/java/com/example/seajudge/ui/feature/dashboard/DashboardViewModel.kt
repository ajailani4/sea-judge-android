package com.example.seajudge.ui.feature.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seajudge.domain.use_case.report.GetReportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getReportsUseCase: GetReportsUseCase
) : ViewModel() {
    var reportsState by mutableStateOf<DashboardState>(DashboardState.Idle)
    var swipeRefreshing by mutableStateOf(false)
    var searchQuery by mutableStateOf<String?>(null)
    var selectedReportImg by mutableStateOf("")
    var fullSizeImgVis by mutableStateOf(false)

    init {
        onEvent(DashboardEvent.LoadReports)
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.LoadReports -> getReports()
        }
    }

    fun onSwipeRefreshingChanged(isRefreshing: Boolean) {
        swipeRefreshing = isRefreshing
    }

    fun onSearchQueryChanged(query: String? = null) {
        searchQuery = query
    }

    fun onSelectedReportImgChanged(image: String) {
        selectedReportImg = image
    }

    fun onFulLSizeImgVisChanged(visibility: Boolean) {
        fullSizeImgVis = visibility
    }

    private fun getReports() {
        viewModelScope.launch {
            reportsState = DashboardState.LoadingReports

            reportsState = try {
                if (searchQuery?.isEmpty() == true) searchQuery = null

                val response = getReportsUseCase.invoke(searchQuery)

                if (response.code() == 200) {
                    DashboardState.Reports(response.body()?.data)
                } else {
                    DashboardState.FailReports("Terjadi kesalahan")
                }
            } catch (e: Exception) {
                DashboardState.ErrorReports("Error")
            }
        }
    }
}