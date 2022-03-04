package com.example.seajudge.domain.use_case.user

import com.example.seajudge.data.model.request.LoginRequest
import com.example.seajudge.data.model.response.BaseResponse
import com.example.seajudge.data.model.response.CredentialResponse
import com.example.seajudge.data.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    private suspend fun login(loginRequest: LoginRequest) = userRepository.login(loginRequest)

    suspend operator fun invoke(loginRequest: LoginRequest): Response<BaseResponse<CredentialResponse>> {
        return login(loginRequest)
    }
}