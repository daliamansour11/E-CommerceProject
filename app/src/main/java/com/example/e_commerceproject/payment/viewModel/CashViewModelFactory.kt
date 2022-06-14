package com.example.e_commerceproject.payment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.details.viewmodel.DetailsViewModel
import com.example.e_commerceproject.network.remotesource.CouponsRepository
import com.example.e_commerceproject.network.remotesource.DetailsRepository

class CashViewModelFactory(private val repository: CouponsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CashViewModel::class.java)) {
            CashViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}