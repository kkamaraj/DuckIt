package com.example.duckit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.duckit.repository.DuckItRepository
import com.example.duckit.repository.LoginError
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
    fun onLoginAction(navController: NavController, username: String, password: String) {
        viewModelScope.launch {
            val result = duckItRepository.login(username, password)
            if(result.isSuccess) {
                result.getOrNull()?.let { loginState.setAuthToken(it) }
                navController.navigate("home")
            } else {
                if (result.exceptionOrNull() is LoginError) {
                    val errorCode = (result.exceptionOrNull() as LoginError).errorCode
                    if (errorCode == 403) {
                        navController.navigate("loginError")
                    } else if (errorCode == 404) {
                        navController.navigate("register")
                    }
                }
            }
        }
    }

    fun onRegisterAction(navController: NavController, username: String, password: String) {
        viewModelScope.launch {
            val result = duckItRepository.register(username, password)
            if(result.isSuccess) {
                result.getOrNull()?.let { loginState.setAuthToken(it) }
            } else {
                if (result.exceptionOrNull() is SignUpError) {
                  navController.navigate("signUpError")
                }
            }
        }
    }

}