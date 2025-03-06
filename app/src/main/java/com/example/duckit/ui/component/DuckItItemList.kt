package com.example.duckit.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.duckit.datamodel.DuckItInfo

@Composable
fun DuckItItemList(
    duckItInfoList: List<DuckItInfo>,
    onUpvoteClick: (String) -> Unit,
    onDownvoteClick: (String) -> Unit,
    modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = duckItInfoList, key = { duckItInfo -> duckItInfo.id }) { duckItInfo ->
            DuckItItem(duckItInfo, onUpvoteClick, onDownvoteClick)
        }
    }
}