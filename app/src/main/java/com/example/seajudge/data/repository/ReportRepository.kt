package com.example.seajudge.data.repository

import com.example.seajudge.data.data_source.remote.ReportRemoteDataSource
import java.io.File
import javax.inject.Inject

class ReportRepository @Inject constructor(
    private val reportRemoteDataSource: ReportRemoteDataSource
) {
    suspend fun getReports(searchQuery: String?) = reportRemoteDataSource.getReports(searchQuery)

    suspend fun getUserReports(username: String) = reportRemoteDataSource.getUserReports(username)

    suspend fun uploadReport(
        username: String,
        photo: File,
        violation: String,
        location: String,
        date: String,
        time: String
    ) = reportRemoteDataSource.uploadReport(
        username = username,
        photo = photo,
        violation = violation,
        location = location,
        date = date,
        time = time
    )

    suspend fun editReport(
        id: Int,
        violation: String,
        location: String,
        date: String,
        time: String
    ) = reportRemoteDataSource.editReport(
        id = id,
        violation = violation,
        location = location,
        date = date,
        time = time
    )
}