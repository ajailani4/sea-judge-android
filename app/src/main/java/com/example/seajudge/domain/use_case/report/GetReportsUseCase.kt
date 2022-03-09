package com.example.seajudge.domain.use_case.report

import com.example.seajudge.data.repository.ReportRepository
import javax.inject.Inject

class GetReportsUseCase @Inject constructor(
    private val reportsRepository: ReportRepository
) {
    private suspend fun getReports(searchQuery: String?) = reportsRepository.getReports(searchQuery)

    suspend operator fun invoke(searchQuery: String?) = getReports(searchQuery)
}