package com.example.seajudge.domain.use_case.user_credential

import com.example.seajudge.data.repository.UserCredentialRepository
import javax.inject.Inject

class GetUsernameUseCase @Inject constructor(
    private val userCredentialRepository: UserCredentialRepository
) {
    private fun getUsername() = userCredentialRepository.getUsername()

    operator fun invoke() = getUsername()
}