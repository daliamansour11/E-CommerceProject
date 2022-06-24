package com.example.e_commerceproject.payment.client

import com.example.e_commerceproject.payment.model.AddedOrderModel
import com.example.e_commerceproject.payment.model.PostOrderModel


interface OrderRemoteSource {

    suspend fun postOrder(order: PostOrderModel): AddedOrderModel
}