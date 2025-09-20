package com.example.authapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authapp.model.User
import com.example.authapp.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel() {

    fun login(email: String, password: String) {

    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user: User = User(name = name, email = email)
            val response = registerUseCase.invoke(user, password)
            Log.d("register_response", "Response: ${response}")
        }
    }
}