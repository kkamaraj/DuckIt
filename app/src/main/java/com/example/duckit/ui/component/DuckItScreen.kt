package com.example.duckit.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.duckit.viewmodel.DuckItViewModel

@Composable
fun DuckItScreen(
    modifier: Modifier = Modifier,
    duckItViewModel: DuckItViewModel = hiltViewModel()
) {
    val duckItInfoList = remember { duckItViewModel.duckItInfoList }
    DuckItItemList(
        duckItInfoList = duckItInfoList,
        onUpvoteClick = {
            duckItViewModel.onUpvoteClick(it)
        },
        onDownvoteClick = {
            duckItViewModel.onDownvoteClick(it)
        },
        modifier = modifier
    )
}