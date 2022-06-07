package com.example.e_commerceproject.profile.client

import com.example.e_commerceproject.profile.model.OrderModel

interface ProfileRemoteSource {
    suspend fun getAllOrders(): List<OrderModel>
}