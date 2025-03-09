package com.example.duckit.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.duckit.viewmodel.DuckItViewModel

@Composable
fun CreatePostScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    duckItViewModel: DuckItViewModel = hiltViewModel()
) {
    var headline by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            DuckItAppBar(title = "Create Post", navController = navController, modifier = modifier)
        },
        modifier = modifier.fillMaxWidth()
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            TextField(
                value = headline,
                singleLine = true,
                onValueChange = { headline = it },
                label = { Text("Headline") },
                modifier = Modifier.padding(16.dp)
            )
            TextField(
                value = imageUrl,
                singleLine = true,
                onValueChange = { imageUrl = it },
                label = { Text("Image Url") },
                modifier = Modifier.padding(16.dp)
            )
            Button(
                enabled = headline.isNotBlank() &&
                        imageUrl.isNotBlank() &&
                        !duckItViewModel.loginState.getAuthToken().isNullOrEmpty(),
                onClick = {
                    duckItViewModel.onCreatePost(headline, imageUrl)
                    navController.navigateUp()
                },
                modifier = modifier.padding(start = 16.dp)
            ) {
                Text(text = "Create Post")
            }

            if(duckItViewModel.loginState.getAuthToken().isNullOrEmpty()) {
                Text(
                    text = "Please login to create post"
                )
            }
        }
    }
}

@Preview
@Composable
private fun CreatePostScreenPreview() {
    CreatePostScreen(navController = NavController(LocalContext.current), modifier = Modifier, duckItViewModel = hiltViewModel())

}
