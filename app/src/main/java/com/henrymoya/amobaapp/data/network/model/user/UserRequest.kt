package com.henrymoya.amobaapp.data.network.model.user

data class UserRequest(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true
)
