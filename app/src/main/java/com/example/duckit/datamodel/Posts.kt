package com.example.duckit.datamodel

import com.google.gson.annotations.SerializedName

data class Posts(
    @SerializedName("Posts") val posts: List<DuckItInfo>
)
