package com.example.e_commerceproject.profile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceproject.profile.model.OrderModel
import com.example.e_commerceproject.profile.model.ProfileRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel (iRepo: ProfileRepositoryInterface) : ViewModel() {

    private val profileRepo: ProfileRepositoryInterface = iRepo
    val orderList = MutableLiveData<List<OrderModel>>()

    fun getAllOrders(customerId: String){
        viewModelScope.launch{
            val brands = profileRepo.getAllOrders(customerId)
            withContext(Dispatchers.Main){
                orderList.postValue(brands)
            }
        }
    }
}