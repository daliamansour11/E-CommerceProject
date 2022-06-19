package com.example.e_commerceproject.profile.model


interface ProfileRepositoryInterface {
    suspend fun getAllOrders(customerId: String): List<OrderModel>
}