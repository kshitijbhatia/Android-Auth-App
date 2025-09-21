package com.example.authapp.dataSource

import android.util.Log
import com.example.authapp.model.User
import com.example.authapp.utils.NetworkResult
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthenticationDataSource {
    override suspend fun register(user: User, password: String): NetworkResult<Unit> {
        return try {
            val response: AuthResult = firebaseAuth.createUserWithEmailAndPassword(user.email, password).await()
            NetworkResult.Success(Unit)
        } catch(error: Exception) {
            NetworkResult.Failure(error.message.toString())
        }
    }

    override suspend fun login(user: User, password: String): NetworkResult<Unit> {
        return try {
            val response: AuthResult = firebaseAuth.signInWithEmailAndPassword(user.email, password).await()
            NetworkResult.Success(Unit)
        } catch(error: Exception) {
            NetworkResult.Failure(error.message.toString())
        }
    }

}