package com.example.seajudge.data.model.request

data class RegisterRequest(
    val username: String,
    val password: String,
    val name: String,
    val state: String,
    val job: String,
    val phoneNumber: String
)
