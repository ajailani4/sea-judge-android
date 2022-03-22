package com.example.seajudge.domain.use_case.report

import com.example.seajudge.data.repository.ReportRepository
import javax.inject.Inject

class DeleteReportUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    private suspend fun deleteReport(id: Int) = reportRepository.deleteReport(id)

    suspend operator fun invoke(id: Int) = deleteReport(id)
}