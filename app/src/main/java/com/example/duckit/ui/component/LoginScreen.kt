package com.example.duckit.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.duckit.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    isSignUp: Boolean = false,
    loginViewModel: LoginViewModel = hiltViewModel(),
    modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            DuckItAppBar(title = "Login", navController = navController, modifier = modifier)
        },
        modifier = modifier.fillMaxWidth()
    ) { innerPadding ->
        LoginInput(
            navController = navController,
            onLoginAction = loginViewModel::onLoginAction,
            onRegisterAction = loginViewModel::onRegisterAction,
            isSignUp = isSignUp,
            modifier = modifier.padding(innerPadding)
        )
    }
}