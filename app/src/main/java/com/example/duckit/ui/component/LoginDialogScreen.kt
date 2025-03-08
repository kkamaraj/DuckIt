package com.example.duckit.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        onDismissRequest = { /* Dismiss the dialog when the user clicks outside of it */ },
        content = {
            Surface(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(modifier = modifier.padding(8.dp)) {
                    Text("Sign Up", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier.padding(8.dp))
                    Text("Account does not exist. Do you want to create an account?")
                    Spacer(modifier.padding(8.dp))
                    Row(horizontalArrangement = Arrangement.End, modifier = modifier.align(Alignment.End)) {
                        TextButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Text("Cancel")
                        }
                        TextButton(onClick = { navController.navigate(Screen.SignUp.route) }) {
                            Text("Create Account")
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginErrorScreen(navController: NavController, modifier: Modifier = Modifier) {
    BasicAlertDialog(
        onDismissRequest = { /* Dismiss the dialog when the user clicks outside of it */ },
        content = {
            Surface(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(modifier = modifier.padding(8.dp)) {
                    Text("Login Error", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier.padding(8.dp))
                    Text("Invalid username or password")
                    Spacer(modifier.padding(8.dp))
                    TextButton(onClick = {
                        navController.navigateUp()
                    }, modifier = modifier.align(Alignment.End)) {
                        Text("Dismiss")
                    }
                }
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpErrorScreen(navController: NavController, modifier: Modifier = Modifier) {
    BasicAlertDialog(
        onDismissRequest = { /* Dismiss the dialog when the user clicks outside of it */ },
        content = {
            Surface(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(modifier = modifier.padding(8.dp)) {
                    Text("Failed to create account", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier.padding(8.dp))
                    Text("Account already exist or failed to create account")
                    Spacer(modifier.padding(8.dp))
                    TextButton(onClick = {
                        navController.navigateUp()
                    }, modifier = modifier.align(Alignment.End)) {
                        Text("Dismiss")
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpSuccessScreen(navController: NavController, modifier: Modifier = Modifier) {
    BasicAlertDialog(
        onDismissRequest = { /* Dismiss the dialog when the user clicks outside of it */ },
        content = {
            Surface(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(modifier = modifier.padding(8.dp)) {
                    Text("Create account", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier.padding(8.dp))
                    Text("Account is successfully created")
                    Spacer(modifier.padding(8.dp))
                    TextButton(onClick = {
                        navController.navigate(Screen.Home.route)
                    }, modifier = modifier.align(Alignment.End)) {
                        Text("Dismiss")
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(navController = NavController(LocalContext.current))
}

@Preview
@Composable
private fun SignUpErrorScreenPreview() {
    SignUpErrorScreen(navController = NavController(LocalContext.current))
}

@Preview
@Composable
private fun LoginErrorScreenPreview() {
    LoginErrorScreen(navController = NavController(LocalContext.current))
}

@Preview
@Composable
private fun SignUpSuccessScreenPreview() {
    SignUpSuccessScreen(navController = NavController(LocalContext.current))
}
