package com.example.authapp.dataSource

import com.example.authapp.model.User
import com.example.authapp.utils.NetworkResult

interface AuthenticationDataSource {
    suspend fun register(user: User, password: String) : NetworkResult<Unit>

    suspend fun login(user: User, password: String) : NetworkResult<Unit>
}