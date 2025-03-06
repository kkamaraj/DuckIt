package com.example.duckit.datamodel

data class DuckItInfo(
    val id: String,
    val headline: String,
    val image: String,
    var upvotes: Int,
    val author: String = "isaac toast"
) {
}
