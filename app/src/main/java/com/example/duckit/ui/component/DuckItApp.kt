package com.example.duckit.ui.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController

/**
 * Sealed class to represent the different routes in the app.
 * This improves type safety and code readability.
 */
sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Login : Screen("login")
    data object SignUp : Screen("signUp")
    data object RegisterDialog : Screen("register")
    data object LoginErrorDialog : Screen("loginError")
    data object SignUpSuccessDialog : Screen("signUpSuccess")
    data object SignUpErrorDialog : Screen("signUpError")
}

@Composable
fun DuckItApp() {
    val navController = rememberNavController()
    DuckItNavHost(navController = navController)
}

@Composable
fun DuckItNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { DuckItScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.SignUp.route) { LoginScreen(navController, isSignUp = true) }
        dialog(Screen.RegisterDialog.route) { SignUpScreen(navController) }
        dialog(Screen.LoginErrorDialog.route) { LoginErrorScreen(navController) }
        dialog(Screen.SignUpSuccessDialog.route) { SignUpSuccessScreen(navController) }
        dialog(Screen.SignUpErrorDialog.route) { SignUpErrorScreen(navController) }
    }
}