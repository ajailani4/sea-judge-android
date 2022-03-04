package com.example.seajudge.data.data_source.remote

import com.example.seajudge.data.api.ApiService
import com.example.seajudge.data.model.request.LoginRequest
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(loginRequest: LoginRequest) = apiService.login(loginRequest)
}