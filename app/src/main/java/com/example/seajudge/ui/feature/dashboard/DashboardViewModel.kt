package com.example.seajudge.ui.feature.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seajudge.domain.use_case.report.GetReportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getReportsUseCase: GetReportsUseCase
) : ViewModel() {
    var reportsState by mutableStateOf<DashboardState>(DashboardState.LoadingReports)
    var selectedReportImg by mutableStateOf("")
    var fullSizeImgVis by mutableStateOf(false)

    fun onSelectedReportImgChanged(image: String) {
        selectedReportImg = image
    }

    fun onFulLSizeImgVisChanged(visibility: Boolean) {
        fullSizeImgVis = visibility
    }

    init {
        onEvent(DashboardEvent.LoadReports)
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.LoadReports -> getReports()
        }
    }

    private fun getReports() {
        viewModelScope.launch {
            reportsState = try {
                val response = getReportsUseCase.invoke()

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