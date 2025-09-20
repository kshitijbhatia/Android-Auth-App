package com.example.authapp.dataSource

import com.example.authapp.model.User
import com.example.authapp.utils.NetworkResult
import javax.inject.Inject

class AuthenticationRepo @Inject constructor(
    private val authenticationDataSource: AuthenticationDataSource
) {
    suspend fun register(user: User, password: String): NetworkResult<Unit> = authenticationDataSource.register(user, password)

    suspend fun login(user: User, password: String): NetworkResult<Unit> = authenticationDataSource.login(user, password)
}