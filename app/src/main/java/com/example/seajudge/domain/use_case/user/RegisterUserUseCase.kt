package com.example.seajudge.domain.use_case.user

import com.example.seajudge.data.model.request.RegisterRequest
import com.example.seajudge.data.repository.UserRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    private suspend fun register(registerRequest: RegisterRequest) =
        userRepository.register(registerRequest)

    suspend operator fun invoke(registerRequest: RegisterRequest) = register(registerRequest)
}