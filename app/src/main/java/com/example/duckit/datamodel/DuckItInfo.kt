package com.example.duckit.datamodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

data class DuckItInfo(
    val id: String,
    val headline: String,
    val image: String,
    var upvotes: Int = 0,
    val author: String = "isaac toast",
) {
    var updatedUpvotes by mutableIntStateOf(0)

    init {
        if (upvotes < 0) {
            upvotes = 0
        }
        updatedUpvotes = upvotes
    }
}

