package com.example.seajudge.data.data_source.remote

import com.example.seajudge.data.api.ApiService
import javax.inject.Inject

class ReportRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getReports() = apiService.getReports()
}