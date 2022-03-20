package com.example.seajudge.data.repository

import com.example.seajudge.data.data_source.local.UserCredentialLocalDataSource
import javax.inject.Inject

class UserCredentialRepository @Inject constructor(
    private val userCredentialLocalDataSource: UserCredentialLocalDataSource
) {
    fun saveUsername(username: String) {
        userCredentialLocalDataSource.saveUsername(username)
    }

    fun getUsername() = userCredentialLocalDataSource.getUsername()

    fun saveAccessToken(accessToken: String) {
        userCredentialLocalDataSource.saveAccessToken(accessToken)
    }

    fun getAccessToken() = userCredentialLocalDataSource.getAccessToken()

    fun deleteUsername() {
        userCredentialLocalDataSource.deleteUsername()
    }

    fun deleteAccessToken() {
        userCredentialLocalDataSource.deleteAccessToken()
    }
}