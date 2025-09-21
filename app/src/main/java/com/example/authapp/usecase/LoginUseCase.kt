package com.example.authapp.usecase

import com.example.authapp.dataSource.AuthenticationRepo
import com.example.authapp.model.User
import com.example.authapp.utils.NetworkResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepo: AuthenticationRepo
) {
    suspend fun invoke(user: User, password: String): NetworkResult<Unit> = authenticationRepo.login(user, password)
}