package com.example.e_commerceproject.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.network.remotesource.CouponsRepository
import java.lang.IllegalArgumentException


class CouponsViewModelFactory (private val _irepo: CouponsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CouponsViewModel::class.java)) {
            CouponsViewModel(_irepo) as T
        } else {
            throw IllegalArgumentException("ViewModel Class not found")
        }
    }
}