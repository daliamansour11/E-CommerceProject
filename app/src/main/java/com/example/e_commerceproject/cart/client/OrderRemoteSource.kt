package com.example.e_commerceproject.cart.client

import com.example.e_commerceproject.cart.model.AddedOrderModel
import com.example.e_commerceproject.cart.model.PostOrderModel


interface OrderRemoteSource {

    suspend fun postOrder(order: PostOrderModel): AddedOrderModel
}