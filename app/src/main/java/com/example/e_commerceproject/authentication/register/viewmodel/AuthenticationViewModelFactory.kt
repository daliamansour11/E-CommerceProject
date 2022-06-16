package com.example.e_commerceproject.authentication.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.category.viewmodel.CategoryViewModel
import com.example.e_commerceproject.data.AuthenticationRepository
import com.example.e_commerceproject.data.CategoryRepository

class AuthenticationViewModelFactory constructor(private val repository: AuthenticationRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AuthenticationViewModel::class.java)) {
            AuthenticationViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}