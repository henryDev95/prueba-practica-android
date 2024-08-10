package com.henrymoya.amobaapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.henrymoya.amobaapp.R
import com.henrymoya.amobaapp.core.model.UserRegister
import com.henrymoya.amobaapp.core.toast.PrintResult
import com.henrymoya.amobaapp.databinding.ActivityRegisterBinding
import com.henrymoya.amobaapp.ui.viewmodel.RegisterUserViewModel
import com.henrymoya.amobaapp.ui.viewmodel.UserViewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private val registerUserViewModel: RegisterUserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            var userRegister = UserRegister(
                email = binding.email.text.toString(),
                password = binding.password.text.toString(),
                confirmPassword = binding.confirmPassword.text.toString()
            )
            registerUserViewModel.createAccount(
                userRegister.email,
                userRegister.password,
                userRegister.confirmPassword
            )
        }

        registerUserViewModel.userRegisterState.observe(this, Observer {
            if(it.isSuccess){
                PrintResult.successResult(this, getString(R.string.register_success))
            }else{
                if(it.isError){
                    PrintResult.errorResult(this,getString(R.string.register_error))
                }else{
                    if(it.isConfirmPassword){
                        PrintResult.errorResult(this,getString(R.string.register_confirm_password))
                    }else{
                        PrintResult.cancelResult(this, getString(R.string.message_cancel))
                    }
                }
            }
        })
    }
}