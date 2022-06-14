package com.example.e_commerceproject.currencyConverter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.network.ConverterRepoInterface
import java.lang.IllegalArgumentException

class ConverterViewModelFactory (private val _iRepo: ConverterRepoInterface) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ConverterViewModel::class.java)) {
            ConverterViewModel(_iRepo) as T
        } else {
            throw IllegalArgumentException("ViewModel Class not found")
        }
    }

}
