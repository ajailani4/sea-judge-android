package com.example.seajudge.domain.use_case.user_credential

import com.example.seajudge.data.repository.UserCredentialRepository
import javax.inject.Inject

class SaveAccessTokenUseCase @Inject constructor(
    private val userCredentialRepository: UserCredentialRepository
) {
    private fun saveAccessToken(accessToken: String) {
        userCredentialRepository.saveAccessToken(accessToken)
    }

    operator fun invoke(accessToken: String) {
        saveAccessToken(accessToken)
    }
}