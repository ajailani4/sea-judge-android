package com.example.seajudge.domain.use_case.report

import com.example.seajudge.data.repository.ReportRepository
import javax.inject.Inject

class EditReportUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    private suspend fun editReport(
        id: Int,
        violation: String,
        location: String,
        date: String,
        time: String
    ) = reportRepository.editReport(
        id = id,
        violation = violation,
        location = location,
        date = date,
        time = time
    )

    suspend operator fun invoke(
        id: Int,
        violation: String,
        location: String,
        date: String,
        time: String
    ) = editReport(
        id = id,
        violation = violation,
        location = location,
        date = date,
        time = time
    )
}