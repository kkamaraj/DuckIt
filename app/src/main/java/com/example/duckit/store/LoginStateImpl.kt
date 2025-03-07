package com.example.duckit.store

class LoginStateImpl: LoginState {
    private var _email: String? = null
    private var _authToken: String? = null

    override fun isUserLoggedIn() = _authToken != null

    override fun getEmail() = _email

    override fun setEmail(email: String) {
        _email = email
    }

    override fun getAuthToken() = _authToken

    override fun setAuthToken(authToken: String) {
        _authToken = authToken
    }

    override fun clearAuthToken() {
        _authToken = null
    }
}