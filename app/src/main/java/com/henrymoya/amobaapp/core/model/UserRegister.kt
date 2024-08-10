package com.henrymoya.amobaapp.core.model

data class UserRegister(
    val email: String,
    val password: String,
    val confirmPassword: String
)
