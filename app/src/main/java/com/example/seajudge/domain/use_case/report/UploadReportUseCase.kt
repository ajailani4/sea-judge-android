package com.example.seajudge.domain.use_case.report

import com.example.seajudge.data.repository.ReportRepository
import java.io.File
import javax.inject.Inject

class UploadReportUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    private suspend fun uploadReport(
        username: String,
        photo: File,
        violation: String,
        location: String,
        date: String,
        time: String
    ) = reportRepository.uploadReport(
        username = username,
        photo = photo,
        violation = violation,
        location = location,
        date = date,
        time = time
    )

    suspend operator fun invoke(
        username: String,
        photo: File,
        violation: String,
        location: String,
        date: String,
        time: String
    ) = uploadReport(
        username = username,
        photo = photo,
        violation = violation,
        location = location,
        date = date,
        time = time
    )
}