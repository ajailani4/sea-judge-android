package com.example.seajudge.domain.use_case.user_credential

import com.example.seajudge.data.repository.UserCredentialRepository
import javax.inject.Inject

class SaveUsernameUseCase @Inject constructor(
    private val userCredentialRepository: UserCredentialRepository
) {
    private fun saveUsername(username: String) {
        userCredentialRepository.saveUsername(username)
    }

    operator fun invoke(username: String) {
        saveUsername(username)
    }
}