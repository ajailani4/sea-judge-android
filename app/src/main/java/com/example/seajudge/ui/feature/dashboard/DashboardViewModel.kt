package com.example.seajudge.ui.feature.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seajudge.domain.use_case.report.GetReportsUseCase
import com.example.seajudge.domain.use_case.user_credential.DeleteAccessTokenUseCase
import com.example.seajudge.domain.use_case.user_credential.DeleteUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getReportsUseCase: GetReportsUseCase,
    private val deleteUsernameUseCase: DeleteUsernameUseCase,
    private val deleteAccessTokenUseCase: DeleteAccessTokenUseCase
) : ViewModel() {
    var reportsState by mutableStateOf<DashboardState>(DashboardState.Idle)
    var logoutState by mutableStateOf<DashboardState>(DashboardState.Idle)
    var searchQuery by mutableStateOf<String?>(null)
    var selectedReportImg by mutableStateOf("")
    var fullSizeImgVis by mutableStateOf(false)
    var logoutConfirmDlgVis by mutableStateOf(false)

    init {
        onEvent(DashboardEvent.LoadReports)
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.LoadReports -> getReports()

            DashboardEvent.Logout -> logout()
        }
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

    fun onLogoutConfirmDlgVisChanged(visibility: Boolean) {
        logoutConfirmDlgVis = visibility
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

    private fun logout() {
        deleteUsernameUseCase.invoke()
        deleteAccessTokenUseCase.invoke()
        logoutState = DashboardState.SuccessLogout
    }
}