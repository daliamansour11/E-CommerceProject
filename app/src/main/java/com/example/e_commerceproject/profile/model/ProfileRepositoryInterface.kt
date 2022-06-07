package com.example.e_commerceproject.profile.model


interface ProfileRepositoryInterface {
    suspend fun getAllOrders(): List<OrderModel>
}