package com.example.duckit.viewmodel

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf

data class DuckItViewData(
    val id: String,
    val headline: String,
    val image: String,
    var upvotes: MutableIntState = mutableIntStateOf(0),
)
