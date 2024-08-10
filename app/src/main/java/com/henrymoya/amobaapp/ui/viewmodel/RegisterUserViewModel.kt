package com.henrymoya.amobaapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.henrymoya.amobaapp.core.model.UserRegisterState
import kotlinx.coroutines.launch

class RegisterUserViewModel:ViewModel() {

    var userRegisterState = MutableLiveData<UserRegisterState>()

    fun createAccount(email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            if(email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                val auth = FirebaseAuth.getInstance()
                if(password == confirmPassword){
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                userRegisterState.postValue(UserRegisterState(isSuccess = true))
                            } else {
                                userRegisterState.postValue(UserRegisterState(isError = true))
                            }
                        }
                }else{
                    userRegisterState.postValue(UserRegisterState(isConfirmPassword = true))
                }
            }else{
                userRegisterState.postValue(UserRegisterState(isEmptyInput = true))
            }
        }
    }
}