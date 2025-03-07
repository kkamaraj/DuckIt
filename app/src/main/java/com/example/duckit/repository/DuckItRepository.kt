package com.example.duckit.repository

import com.example.duckit.datamodel.DuckItInfo
import com.example.duckit.datamodel.LoginData
import com.example.duckit.network.DuckItApiService
import com.example.duckit.store.LoginState
import java.io.IOException
import javax.inject.Inject

class LoginError(val errorCode: Int): IOException()
class SignUpError(val errorCode: Int): IOException()

class DuckItRepository @Inject constructor(
    private val loginState: LoginState,
    private val duckItApiService: DuckItApiService) {

    suspend fun getDuckItInfo(): Result<List<DuckItInfo>> {
        val response = duckItApiService.getDuckItList(authToken = loginState.getAuthToken())
        if(response.isSuccessful) {
            return Result.success(response.body()?.posts ?: emptyList())
        } else {
            return Result.failure(Exception(response.errorBody()?.string()))
        }
    }

    suspend fun upvote(id: String) {
        loginState.getAuthToken()?.let { duckItApiService.upVoteDuckItInfo(it, id) }
    }

    suspend fun downvote(id: String) {
        loginState.getAuthToken()?.let { duckItApiService.downVoteDuckItInfo(it, id) }
    }

    suspend fun login(username: String, password: String): Result<String?> {
        val response = duckItApiService.login(LoginData(username, password))
        return if(response.isSuccessful) {
            Result.success(response.body()?.token)
        } else {
            Result.failure(LoginError(response.code()))
        }
    }

    suspend fun register(username: String, password: String): Result<String?> {
        val response = duckItApiService.signUp(LoginData(username, password))
        return if(response.isSuccessful) {
            Result.success(response.body()?.token)
        } else {
            Result.failure(SignUpError(response.code()))
        }
    }
}
