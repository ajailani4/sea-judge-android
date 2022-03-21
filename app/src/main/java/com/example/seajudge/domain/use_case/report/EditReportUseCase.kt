package com.example.seajudge.domain.use_case.report

import com.example.seajudge.data.repository.ReportRepository
import javax.inject.Inject

class EditReportUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    private suspend fun editReport(
        violation: String,
        location: String,
        date: String,
        time: String
    ) = reportRepository.editReport(
        violation = violation,
        location = location,
        date = date,
        time = time
    )

    suspend operator fun invoke(
        violation: String,
        location: String,
        date: String,
        time: String
    ) = editReport(
        violation = violation,
        location = location,
        date = date,
        time = time
    )
}