package com.example.seajudge.ui.feature.edit_report

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seajudge.domain.use_case.report.EditReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditReportViewModel @Inject constructor(
    private val editReportUseCase: EditReportUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var editReportState by mutableStateOf<EditReportState>(EditReportState.Idle)
    var backConfirmDlgVis by mutableStateOf(false)
    var violation by mutableStateOf(savedStateHandle.get<String>("violation")!!)
    var location by mutableStateOf(savedStateHandle.get<String>("location")!!)
    var date by mutableStateOf(savedStateHandle.get<String>("date")!!)
    var time by mutableStateOf(savedStateHandle.get<String>("time")!!)

    fun onEvent(event: EditReportEvent) {
        when (event) {
            EditReportEvent.EditReport -> editReport()
        }
    }

    fun onBackConfirmDlgVisChanged(visibility: Boolean) {
        backConfirmDlgVis = visibility
    }

    fun onViolationChanged(text: String) {
        violation = text
    }

    fun onLocationChanged(text: String) {
        location = text
    }

    fun onDateChanged(text: String) {
        date = text
    }

    fun onTimeChanged(text: String) {
        time = text
    }

    private fun editReport() {
        viewModelScope.launch {
            editReportState = EditReportState.EditingReport

            editReportState = try {
                val response = editReportUseCase.invoke(
                    id = savedStateHandle.get<Int>("id")!!,
                    violation = violation,
                    location = location,
                    date = date,
                    time = time
                )

                if (response.code() == 200) {
                    EditReportState.SuccessEditReport
                } else {
                    EditReportState.FailEditReport("Terjadi kesalahan")
                }
            } catch (e: Exception) {
                EditReportState.ErrorEditReport("Error")
            }
        }
    }
}