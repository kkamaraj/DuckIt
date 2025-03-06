package com.example.duckit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duckit.repository.DuckItRepository
import com.example.duckit.repository.SignUpError
import com.example.duckit.store.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginState: LoginState,
    private val duckItRepository: DuckItRepository
): ViewModel() {
    fun onLoginAction(username: String, password: String) {
        viewModelScope.launch {
            duckItRepository.login(username, password)
        }
    }

    fun onRegisterAction(username: String, password: String) {
        viewModelScope.launch {
            val result = duckItRepository.register(username, password)
            if(result.isSuccess) {
                result.getOrNull()?.let { loginState.setAuthToken(it) }
            } else {
                if (result.exceptionOrNull() is SignUpError) {
                  //TODO show error
                }
            }
        }
    }

}