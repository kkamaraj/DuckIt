package com.example.duckit.network

import com.example.duckit.datamodel.AuthToken
import com.example.duckit.datamodel.CreatePostRequest
import com.example.duckit.datamodel.LoginData
import com.example.duckit.datamodel.Posts
import com.example.duckit.datamodel.UpvoteCount
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface DuckItApiService {

    @POST("signup")
    suspend fun signUp(@Body loginData: LoginData): Response<AuthToken>

    @POST("signin")
    suspend fun login(@Body loginData: LoginData): Response<AuthToken>

    @GET("posts")
    suspend fun getDuckItList(@Header("Authorization") authToken: String?): Response<Posts>

    @POST("posts/{id}/upvote")
    suspend fun upVoteDuckItInfo(@Header("Authorization") authToken: String, @Path("id") id: String): Response<UpvoteCount>

    @POST("posts/{id}/downvote")
    suspend fun downVoteDuckItInfo(@Header("Authorization") authToken: String, @Path("id") id: String): Response<UpvoteCount>

    @POST("posts")
    suspend fun createNewPost(@Header("Authorization") authToken: String, @Body createPostRequest: CreatePostRequest)
}