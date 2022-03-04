package com.example.seajudge.domain.use_case.user_credential

import com.example.seajudge.data.repository.UserCredentialRepository
import javax.inject.Inject

class DeleteAccessTokenUseCase @Inject constructor(
    private val userCredentialRepository: UserCredentialRepository
) {
    private fun deleteAccessToken() {
        userCredentialRepository.deleteAccessToken()
    }

    operator fun invoke() {
        deleteAccessToken()
    }
}