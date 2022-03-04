package com.example.seajudge.domain.use_case.user_credential

import com.example.seajudge.data.repository.UserCredentialRepository
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val userCredentialRepository: UserCredentialRepository
) {
    private fun getAccessToken() = userCredentialRepository.getAccessToken()

    operator fun invoke() = getAccessToken()
}