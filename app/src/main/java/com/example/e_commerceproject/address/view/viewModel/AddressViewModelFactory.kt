package com.example.e_commerceproject.address.view.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.network.remotesource.AdressRepository
import java.lang.IllegalArgumentException

class AddressViewModelFactory (private val repo_Address: AdressRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AddressViewModel::class.java)) {
            AddressViewModel(this.repo_Address) as T
        } else {
            throw IllegalArgumentException("ViewModel Class not found")

        }
    }
}