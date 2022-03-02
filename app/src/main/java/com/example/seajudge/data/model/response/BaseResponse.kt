package com.example.seajudge.data.model.response

data class BaseResponse<T>(
    val code: Int,
    val status: String,
    val data: T
)
