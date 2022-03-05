package com.example.seajudge.data.repository

import com.example.seajudge.data.data_source.remote.UserRemoteDataSource
import com.example.seajudge.data.model.request.LoginRequest
import com.example.seajudge.data.model.request.RegisterRequest
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend fun login(loginRequest: LoginRequest) = userRemoteDataSource.login(loginRequest)

    suspend fun register(registerRequest: RegisterRequest) = userRemoteDataSource.register(registerRequest)
}