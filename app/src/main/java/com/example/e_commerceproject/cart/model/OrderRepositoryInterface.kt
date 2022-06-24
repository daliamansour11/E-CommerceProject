package com.example.e_commerceproject.cart.model

interface OrderRepositoryInterface {

    suspend fun postOrder(order: PostOrderModel): AddedOrderModel
}