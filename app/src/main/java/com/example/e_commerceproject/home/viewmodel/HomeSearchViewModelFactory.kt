package com.example.e_commerceproject.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.home.model.HomeRepositoryInterface
import java.lang.IllegalArgumentException

class HomeSearchViewModelFactory (private val _irepo: HomeRepositoryInterface) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeSearchViewModel::class.java)) {
            HomeSearchViewModel(_irepo) as T
        } else {
            throw IllegalArgumentException("ViewModel Class not found")
        }
    }
}