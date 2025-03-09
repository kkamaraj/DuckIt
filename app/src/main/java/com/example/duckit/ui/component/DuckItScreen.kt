package com.example.duckit.ui.component

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.duckit.viewmodel.DuckItViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DuckItScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    duckItViewModel: DuckItViewModel = hiltViewModel()
) {
    var authToken by remember { mutableStateOf(duckItViewModel.loginState.getAuthToken()) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DuckIt", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    Button(
                        onClick = {
                            if(duckItViewModel.loginState.getAuthToken() == null) {
                                navController.navigate(Screen.Login.route)
                            } else {
                                duckItViewModel.loginState.clearAuthToken()
                                authToken = null
                            }
                        }
                    ) {
                        if(authToken == null) {
                            Text("Login")
                        } else {
                            Text("Logout")
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { (navController.context as? Activity)?.finish() }) {
                        Icon(Icons.Filled.Close, contentDescription = "Close")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.CreatePost.route)
                }
            ) {
                Surface {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        duckItViewModel.initDuckItInfoList()
        val duckItInfoList = remember { duckItViewModel.duckItViewDataList }
        DuckItItemList(
            duckItInfoList = duckItInfoList,
            onUpvoteClick = {
                duckItViewModel.onUpvoteClick(it)
            },
            onDownvoteClick = {
                duckItViewModel.onDownvoteClick(it)
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
private fun DuckItScreenPreview() {
    DuckItScreen(navController = NavController(LocalContext.current))
}