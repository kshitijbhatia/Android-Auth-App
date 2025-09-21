package com.example.authapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authapp.model.User
import com.example.authapp.usecase.LoginUseCase
import com.example.authapp.usecase.RegisterUseCase
import com.example.authapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Initial)
    val uiState: StateFlow<NetworkResult<Unit>> = _uiState

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(email = email)
            val response = loginUseCase.invoke(user, password)
            _uiState.value = response
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user: User = User(name = name, email = email)
            val response = registerUseCase.invoke(user, password)
            _uiState.value = response
        }
    }
}