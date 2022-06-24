package com.example.e_commerceproject.cart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.cart.model.OrderRepositoryInterface


class OrderViewModelFactory  (private val _irepo: OrderRepositoryInterface) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            OrderViewModel(_irepo)as T
        } else {
            throw IllegalArgumentException("ViewModel Class not found")
        }
    }
}