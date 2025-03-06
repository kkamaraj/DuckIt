package com.example.duckit.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.duckit.R
import com.example.duckit.datamodel.DuckItInfo

@Composable
fun DuckItItem(
    duckItInfo: DuckItInfo,
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
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)) {
            IconButton(
                onClick = { onUpvoteClick(duckItInfo.id) }
            ) {
                Icon(Icons.Default.KeyboardArrowUp, contentDescription = null, tint = Color.Green)
            }
            Text(text = duckItInfo.updatedUpvoteCount.toString())
            IconButton(
                enabled = duckItInfo.updatedUpvoteCount > 0,
                onClick = { onDownvoteClick(duckItInfo.id) }
            ) {
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Red)
            }
        }

    }
}

@Preview
@Composable
private fun DuckItItemPreview() {
    DuckItItem(DuckItInfo(
        id = "Eg8KBFBvc3QQgICAuNPXhwo",
        headline = "isaac toast",
        image = "https://howtodrawforkids.com/wp-content/uploads/2024/10/Draw-a-duck-step-9.jpeg",
        upvoteCount = 10
    ), {}, {})
}