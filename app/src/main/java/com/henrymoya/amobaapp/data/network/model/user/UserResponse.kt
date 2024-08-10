package com.henrymoya.amobaapp.data.network.model.user

data class UserResponse(
    var isSuccess:Boolean = false,
    var isError:Boolean  = false,
    var isEmptyInput:Boolean  = false,
    var email:String = ""
)
