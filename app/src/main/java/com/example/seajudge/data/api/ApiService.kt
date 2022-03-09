package com.example.seajudge.data.api

import com.example.seajudge.data.model.Report
import com.example.seajudge.data.model.request.LoginRequest
import com.example.seajudge.data.model.request.RegisterRequest
import com.example.seajudge.data.model.response.BaseResponse
import com.example.seajudge.data.model.response.CredentialResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<BaseResponse<CredentialResponse>>

    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<BaseResponse<CredentialResponse>>

    @GET("reports")
    suspend fun getReports(
        @Query("searchQuery") searchQuery: String?
    ): Response<BaseResponse<List<Report>>>
}