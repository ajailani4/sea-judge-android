package com.example.seajudge.data.data_source.remote

import com.example.seajudge.data.api.ApiService
import com.example.seajudge.data.model.response.BaseResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class ReportRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getReports(searchQuery: String?) = apiService.getReports(searchQuery)

    suspend fun getUserReports(username: String) = apiService.getUserReports(username)

    suspend fun uploadReport(
        username: String,
        photo: File,
        violation: String,
        location: String,
        date: String,
        time: String
    ): Response<BaseResponse<Any>> {
        val usernamePart = username.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val photoPart = MultipartBody.Part.createFormData(
            "photo",
            photo.name,
            photo.asRequestBody("image/*".toMediaTypeOrNull())
        )
        val violationPart = violation.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val locationPart = location.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val datePart = date.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val timePart = time.toRequestBody("multipart/form-data".toMediaTypeOrNull())

        return apiService.uploadReport(
            username = usernamePart,
            photo = photoPart,
            violation = violationPart,
            location = locationPart,
            date = datePart,
            time = timePart
        )
    }

    suspend fun editReport(
        id: Int,
        violation: String,
        location: String,
        date: String,
        time: String
    ) = apiService.editReport(
        id = id,
        violation = violation,
        location = location,
        date = date,
        time = time
    )

    suspend fun deleteReport(id: Int) = apiService.deleteReport(id)
}