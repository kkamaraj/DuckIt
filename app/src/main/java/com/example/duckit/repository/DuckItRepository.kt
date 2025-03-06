package com.example.duckit.repository

import com.example.duckit.datamodel.DuckItInfo
import com.example.duckit.network.DuckItApiService
import com.example.duckit.store.LoginState
import javax.inject.Inject

class DuckItRepository @Inject constructor(
    private val loginState: LoginState,
    private val duckItApiService: DuckItApiService) {

    suspend fun getDuckItInfo(): List<DuckItInfo> {
        val posts = duckItApiService.getDuckItList(authToken = loginState.getAuthToken())
        return posts.posts
    }

}
