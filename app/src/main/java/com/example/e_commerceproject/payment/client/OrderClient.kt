package com.example.e_commerceproject.payment.client

import com.example.e_commerceproject.payment.model.AddedOrderModel
import com.example.e_commerceproject.payment.model.PostOrderModel
import com.example.e_commerceproject.common.network.RetrofitAPIClient


class OrderClient private constructor(): OrderRemoteSource {

    companion object{
        private var instance: OrderClient? = null
        fun getInstance(): OrderClient {
            return  instance?: OrderClient()
        }
    }

    override suspend fun postOrder(order: PostOrderModel): AddedOrderModel {
        var apiClient = RetrofitAPIClient.getAPIClientInstance()
        var postedOrder = apiClient.postOrder(order)
        return postedOrder.order
    }


}