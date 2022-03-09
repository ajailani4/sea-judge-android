package com.example.seajudge.domain.use_case.report

import com.example.seajudge.data.repository.ReportRepository
import javax.inject.Inject

class GetUserReportsUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    private suspend fun getUserReports(username: String) = reportRepository.getUserReports(username)

    suspend operator fun invoke(username: String) = getUserReports(username)
}