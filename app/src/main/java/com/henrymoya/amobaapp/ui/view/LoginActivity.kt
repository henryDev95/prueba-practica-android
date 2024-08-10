package com.henrymoya.amobaapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.henrymoya.amobaapp.R
import com.henrymoya.amobaapp.core.sesion.SessionManagement
import com.henrymoya.amobaapp.core.toast.PrintResult
import com.henrymoya.amobaapp.data.network.model.user.UserRequest
import com.henrymoya.amobaapp.databinding.ActivityLoginBinding
import com.henrymoya.amobaapp.ui.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val userViewModel: UserViewModel by viewModels()
    lateinit var  sessionManagement : SessionManagement
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManagement = SessionManagement(this)
        binding.apply {
            loginButton.setOnClickListener {
                val user = UserRequest(
                    emailInput.editableText.toString(),
                    passwordInput.editableText.toString()
                )
                userViewModel.login(user)
                borrarCampos()
            }
        }

        userViewModel.userResponse.observe(this, Observer {
            if(it.isSuccess){
                sessionManagement.setToken(it.email)
                finish()
                val intent = Intent(this@LoginActivity, DogActivity::class.java)
                startActivity(intent)
                PrintResult.successResult(this, getString(R.string.message_success))
            }else{
                if(it.isError){
                    PrintResult.errorResult(this,getString(R.string.message_error))
                }else{
                    PrintResult.cancelResult(this, getString(R.string.message_cancel))
                }
            }
        })

        binding.register.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun borrarCampos() {
        binding.emailInput.setText("")
        binding.passwordInput.setText("")
    }
}