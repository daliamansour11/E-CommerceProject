package com.example.e_commerceproject.homesearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.homesearch.model.HomeSearchRepositoryInterface
import java.lang.IllegalArgumentException

class HomeSearchViewModelFactory (private val _irepo: HomeSearchRepositoryInterface) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeSearchViewModel::class.java)) {
            HomeSearchViewModel(_irepo) as T
        } else {
            throw IllegalArgumentException("ViewModel Class not found")
        }
    }
}