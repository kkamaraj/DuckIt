package com.example.duckit.store

interface LoginState {
    fun isUserLoggedIn(): Boolean
    fun getEmail(): String?
    fun setEmail(email: String)
    fun getAuthToken(): String?
    fun setAuthToken(authToken: String)
}