package com.example.e_commerceproject.payment.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceproject.payment.model.AddedOrderModel
import com.example.e_commerceproject.payment.model.OrderRepositoryInterface
import com.example.e_commerceproject.payment.model.PostOrderModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OrderViewModel (iRepo: OrderRepositoryInterface) : ViewModel() {

    private val orderRepo: OrderRepositoryInterface = iRepo
    val addedOrder = MutableLiveData<AddedOrderModel>()

    fun postOrder(order: AddedOrderModel) {
        viewModelScope.launch{
            var postOrderModel = PostOrderModel(order = order)
            val addedOrderModel = orderRepo.postOrder(postOrderModel)
            withContext(Dispatchers.Main){
                addedOrder.postValue(addedOrderModel)
            }
        }
    }
}