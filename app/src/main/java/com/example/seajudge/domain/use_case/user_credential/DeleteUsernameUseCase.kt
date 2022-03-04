package com.example.seajudge.domain.use_case.user_credential

import com.example.seajudge.data.repository.UserCredentialRepository
import javax.inject.Inject

class DeleteUsernameUseCase @Inject constructor(
    private val userCredentialRepository: UserCredentialRepository
) {
    private fun deleteUsername() {
        userCredentialRepository.deleteUsername()
    }

    operator fun invoke() {
        deleteUsername()
    }
}