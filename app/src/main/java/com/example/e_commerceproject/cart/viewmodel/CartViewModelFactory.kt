package com.example.e_commerceproject.cart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.network.ConverterRepository
import com.example.e_commerceproject.network.remotesource.CartRepository
import java.lang.IllegalArgumentException

class CartViewModelFactory (private val rpoo_cart : CartRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CartViewModel::class.java)){
            CartViewModel(this.rpoo_cart) as T
        }else{
            throw IllegalArgumentException("ViewModel Class not found")

        }
    }
}