package com.example.seajudge.ui.feature.my_reports

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seajudge.domain.use_case.report.GetUserReportsUseCase
import com.example.seajudge.domain.use_case.user_credential.GetUsernameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyReportsViewModel @Inject constructor(
    private val getMyReportsUseCase: GetUserReportsUseCase,
    private val getUsernameUseCase: GetUsernameUseCase
) : ViewModel() {
    var myReportsState by mutableStateOf<MyReportsState>(MyReportsState.LoadingMyReports)
    var selectedReportImg by mutableStateOf("")
    var fullSizeImgVis by mutableStateOf(false)

    init {
        onEvent(MyReportsEvent.LoadMyReports)
    }

    fun onSelectedReportImgChanged(image: String) {
        selectedReportImg = image
    }

    fun onFulLSizeImgVisChanged(visibility: Boolean) {
        fullSizeImgVis = visibility
    }

    private fun onEvent(event: MyReportsEvent) {
        when (event) {
            MyReportsEvent.LoadMyReports -> getMyReports()
        }
    }

    private fun getMyReports() {
        viewModelScope.launch {
            myReportsState = try {
                val response = getMyReportsUseCase.invoke(getUsernameUseCase.invoke()!!)

                if (response.code() == 200) {
                    MyReportsState.MyReports(response.body()?.data)
                } else {
                    MyReportsState.Fail("Terjadi kesalahan")
                }
            } catch (e: Exception) {
                MyReportsState.Error("Error")
            }
        }
    }
}