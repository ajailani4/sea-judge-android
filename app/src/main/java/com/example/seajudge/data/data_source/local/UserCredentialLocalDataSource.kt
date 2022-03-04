package com.example.seajudge.data.data_source.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserCredentialLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sharedPref = context.getSharedPreferences("userCredential", Context.MODE_PRIVATE)

    companion object {
        const val USERNAME = "username"
        const val ACCESS_TOKEN = "accessToken"
    }

    fun saveUsername(username: String) {
        sharedPref.edit()
            .putString(USERNAME, username)
            .apply()
    }

    fun getUsername() = sharedPref.getString(USERNAME, "")

    fun saveAccessToken(accessToken: String) {
        sharedPref.edit()
            .putString(ACCESS_TOKEN, accessToken)
            .apply()
    }

    fun getAccessToken() = sharedPref.getString(ACCESS_TOKEN, "")

    fun deleteUsername() {
        sharedPref.edit()
            .remove(USERNAME)
            .apply()
    }

    fun deleteAccessToken() {
        sharedPref.edit()
            .remove(ACCESS_TOKEN)
            .apply()
    }
}