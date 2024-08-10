package com.henrymoya.amobaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.henrymoya.amobaapp.data.network.model.user.UserRequest
import com.henrymoya.amobaapp.data.network.model.user.UserResponse
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {

    var userResponse = MutableLiveData<UserResponse>()

    fun login(user : UserRequest){
        viewModelScope.launch {
            if(user.email.isNotEmpty() && user.password.isNotEmpty()){
                val auth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(user.email, user.password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            userResponse.postValue(UserResponse(isSuccess = true, email = user.email))
                        } else {
                            userResponse.postValue(UserResponse(isError = true))
                        }
                    }
            }else{
                userResponse.postValue(UserResponse(isEmptyInput = true))
            }
        }
    }
}