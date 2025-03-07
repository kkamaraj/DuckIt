package com.example.duckit.ui.component

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController

@Composable
fun DuckItApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { DuckItScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signUp") { LoginScreen(navController, isSignUp = true) }
        dialog("register") { SignUpScreen(navController) }
        dialog("loginError") { LoginErrorScreen(navController) }
        dialog("signUpError") { SignUpErrorScreen(navController) }
    }
}