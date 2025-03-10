package com.example.duckit.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.duckit.viewmodel.DuckItViewData

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DuckItItem(
    duckItInfo: DuckItViewData,
    onUpvoteClick: (String) -> Unit,
    onDownvoteClick: (String) -> Unit,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.Start
    ) {
        Text(
            text = duckItInfo.headline,
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.titleMedium
        )
        GlideImage(
            model = duckItInfo.image,
            contentDescription = null,
            modifier = Modifier.padding(bottom = 8.dp).width(256.dp).height(256.dp)
        )
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { onUpvoteClick(duckItInfo.id) }
            ) {
                Icon(Icons.Default.KeyboardArrowUp, contentDescription = null, tint = Color.Green)
            }
            Text(text = duckItInfo.upvotes.intValue.toString())
            IconButton(
                enabled = duckItInfo.upvotes.intValue > 0,
                onClick = { onDownvoteClick(duckItInfo.id) }
            ) {
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Red)
            }
        }

    }
}