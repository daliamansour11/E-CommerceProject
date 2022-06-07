package com.example.e_commerceproject.profile.client


import com.example.e_commerceproject.common.network.RetrofitAPIClient
import com.example.e_commerceproject.profile.model.OrderModel

class ProfileClient private constructor():ProfileRemoteSource{
    companion object{
        private var instance: ProfileClient? = null
        fun getInstance(): ProfileClient {
            return  instance?: ProfileClient()
        }
    }

    override suspend fun getAllOrders(): List<OrderModel> {
        var apiClient = RetrofitAPIClient.getAPIClientInstance()
        var response = apiClient.getAllOrderResponse("id,total_price,created_at,customer,line_items")
        return response.orders
    }
}