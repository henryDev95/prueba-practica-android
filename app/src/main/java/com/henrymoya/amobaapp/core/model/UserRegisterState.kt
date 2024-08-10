package com.henrymoya.amobaapp.core.model

data class UserRegisterState(
    var isSuccess:Boolean = false,
    var isError:Boolean  = false,
    var isEmptyInput:Boolean  = false,
    var isConfirmPassword:Boolean =false
)
