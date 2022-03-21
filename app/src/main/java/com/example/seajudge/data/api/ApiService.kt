package com.example.seajudge.data.api

import com.example.seajudge.data.model.Report
import com.example.seajudge.data.model.request.LoginRequest
import com.example.seajudge.data.model.request.RegisterRequest
import com.example.seajudge.data.model.response.BaseResponse
import com.example.seajudge.data.model.response.CredentialResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

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

    @GET("users/{username}/reports")
    suspend fun getUserReports(
        @Path("username") username: String
    ): Response<BaseResponse<List<Report>>>

    @Multipart
    @POST("reports")
    suspend fun uploadReport(
        @Part("username") username: RequestBody,
        @Part photo: MultipartBody.Part,
        @Part("violation") violation: RequestBody,
        @Part("location") location: RequestBody,
        @Part("date") date: RequestBody,
        @Part("time") time: RequestBody
    ): Response<BaseResponse<Any>>

    @FormUrlEncoded
    @PUT("reports/{id}")
    suspend fun editReport(
        @Field("violation") violation: String,
        @Field("location") location: String,
        @Field("date") date: String,
        @Field("time") time: String
    ): Response<BaseResponse<Any>>
}