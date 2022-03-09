package com.example.seajudge.data.repository

import com.example.seajudge.data.data_source.remote.ReportRemoteDataSource
import javax.inject.Inject

class ReportRepository @Inject constructor(
    private val reportRemoteDataSource: ReportRemoteDataSource
) {
    suspend fun getReports(searchQuery: String?) = reportRemoteDataSource.getReports(searchQuery)

    suspend fun getUserReports(username: String) = reportRemoteDataSource.getUserReports(username)
}