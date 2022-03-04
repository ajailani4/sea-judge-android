package com.example.seajudge.domain.use_case.user

import com.example.seajudge.data.model.request.LoginRequest
import com.example.seajudge.data.repository.UserRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    private suspend fun login(loginRequest: LoginRequest) = userRepository.login(loginRequest)

    suspend operator fun invoke(loginRequest: LoginRequest) = login(loginRequest)
}