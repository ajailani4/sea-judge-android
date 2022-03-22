package com.example.seajudge.ui.feature.my_reports

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seajudge.domain.use_case.report.DeleteReportUseCase
import com.example.seajudge.domain.use_case.report.GetUserReportsUseCase
import com.example.seajudge.domain.use_case.user_credential.GetUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyReportsViewModel @Inject constructor(
    private val getMyReportsUseCase: GetUserReportsUseCase,
    private val getUsernameUseCase: GetUsernameUseCase,
    private val deleteReportUseCase: DeleteReportUseCase
) : ViewModel() {
    var myReportsState by mutableStateOf<MyReportsState>(MyReportsState.LoadingMyReports)
    var deleteReportState by mutableStateOf<MyReportsState>(MyReportsState.Idle)
    var selectedReportImg by mutableStateOf("")
    var fullSizeImgVis by mutableStateOf(false)
    private var deletedReport by mutableStateOf(0)
    var deletedReportDlgVis by mutableStateOf(false)

    init {
        onEvent(MyReportsEvent.LoadMyReports)
    }

    fun onEvent(event: MyReportsEvent) {
        when (event) {
            MyReportsEvent.LoadMyReports -> getMyReports()

            MyReportsEvent.DeleteReport -> deleteReport()
        }
    }

    fun onSelectedReportImgChanged(image: String) {
        selectedReportImg = image
    }

    fun onFulLSizeImgVisChanged(visibility: Boolean) {
        fullSizeImgVis = visibility
    }

    fun onDeletedReportChanged(id: Int) {
        deletedReport = id
    }

    fun onDeletedReportDlgVisChanged(visibility: Boolean) {
        deletedReportDlgVis = visibility
    }

    private fun getMyReports() {
        viewModelScope.launch {
            myReportsState = MyReportsState.LoadingMyReports

            myReportsState = try {
                val response = getMyReportsUseCase.invoke(getUsernameUseCase.invoke()!!)

                if (response.code() == 200) {
                    MyReportsState.MyReports(response.body()?.data)
                } else {
                    MyReportsState.FailMyReports("Terjadi kesalahan")
                }
            } catch (e: Exception) {
                MyReportsState.ErrorMyReports("Error")
            }
        }
    }

    private fun deleteReport() {
        viewModelScope.launch {
            deleteReportState = MyReportsState.DeletingReport

            deleteReportState = try {
                val response = deleteReportUseCase.invoke(deletedReport)

                if (response.code() == 200) {
                    MyReportsState.SuccessDeleteReport
                } else {
                    MyReportsState.FailDeleteReport("Terjadi kesalahan")
                }
            } catch (e: Exception) {
                MyReportsState.ErrorDeleteReport("Error")
            }
        }
    }
}