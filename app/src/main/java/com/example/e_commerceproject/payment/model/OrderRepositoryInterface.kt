package com.example.e_commerceproject.payment.model

interface OrderRepositoryInterface {

    suspend fun postOrder(order: PostOrderModel): AddedOrderModel
}