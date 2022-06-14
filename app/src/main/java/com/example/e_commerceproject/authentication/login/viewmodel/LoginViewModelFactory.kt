package com.example.e_commerceproject.authentication.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.authentication.register.viewmodel.AuthenticationViewModel
import com.example.e_commerceproject.data.AuthenticationRepository

class LoginViewModelFactory constructor(private val repository: AuthenticationRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}