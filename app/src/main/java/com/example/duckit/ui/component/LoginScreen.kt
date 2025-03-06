package com.example.duckit.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.duckit.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    modifier: Modifier = Modifier) {

    LoginInput(
        onLoginAction = loginViewModel::onLoginAction,
        modifier = modifier
    )
}