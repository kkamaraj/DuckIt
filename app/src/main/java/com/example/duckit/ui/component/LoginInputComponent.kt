package com.example.duckit.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginInput(
    navController: NavController,
    onLoginAction: (navController: NavController, username: String, password: String) -> Unit,
    onRegisterAction: (navController: NavController, username: String, password: String) -> Unit,
    isSignUp: Boolean = false,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        TextField(
            value = username,
            singleLine = true,
            onValueChange = { username = it },
            label = { Text("Email") },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = password,
            singleLine = true,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (isSignUp) {
            TextField(
                value = confirmPassword,
                singleLine = true,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }

        if (!isSignUp) {
            Button(
                enabled = username.isNotBlank() && password.isNotBlank(),
                onClick = { onLoginAction(navController, username, password) }
            ) {
                Text(text = "Login")
            }
        } else {
            Button(
                enabled = username.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank(),
                onClick = { onRegisterAction(navController, username, password) }
            ) {
                Text(text = "Register")
            }
        }
    }
}

@Preview
@Composable
private fun LoginInputPreview() {
    LoginInput(navController = NavController(LocalContext.current), onLoginAction = { _, _, _ -> }, onRegisterAction = { _, _, _ -> })

}