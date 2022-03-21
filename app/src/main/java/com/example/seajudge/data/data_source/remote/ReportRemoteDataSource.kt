package com.example.seajudge.data.data_source.remote

import com.example.seajudge.data.api.ApiService
import com.example.seajudge.data.model.response.BaseResponse
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
        val usernamePart = RequestBody.create(MediaType.parse("multipart/form-data"), username)
        val photoPart = MultipartBody.Part.createFormData(
            "photo",
            photo.name,
            RequestBody.create(MediaType.parse("image/*"), photo)
        )
        val violationPart = RequestBody.create(MediaType.parse("multipart/form-data"), violation)
        val locationPart = RequestBody.create(MediaType.parse("multipart/form-data"), location)
        val datePart = RequestBody.create(MediaType.parse("multipart/form-data"), date)
        val timePart = RequestBody.create(MediaType.parse("multipart/form-data"), time)

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
}