package com.example.seajudge.domain.use_case.report

import com.example.seajudge.data.repository.ReportRepository
import javax.inject.Inject

class GetReportsUseCase @Inject constructor(
    private val reportsRepository: ReportRepository
) {
    private suspend fun getReports() = reportsRepository.getReports()

    suspend operator fun invoke() = getReports()
}