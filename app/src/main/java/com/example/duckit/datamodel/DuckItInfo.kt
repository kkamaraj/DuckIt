package com.example.duckit.datamodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

data class DuckItInfo(
    val id: String,
    val headline: String,
    val image: String,
    val upvoteCount: Int,
) {
    var updatedUpvoteCount by mutableIntStateOf(upvoteCount)
}
