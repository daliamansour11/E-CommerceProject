package com.example.e_commerceproject.payment.model

import android.content.Context
import com.example.e_commerceproject.payment.client.OrderRemoteSource


class OrderRepository private constructor(
    var remoteSource: OrderRemoteSource,
    var context: Context
) : OrderRepositoryInterface {

    companion object{
        private var instance: OrderRepository? = null
        fun getInstance(remoteSource: OrderRemoteSource, context: Context): OrderRepository {
            return instance ?: OrderRepository(
                remoteSource, context)
        }
    }

    override suspend fun postOrder(order: PostOrderModel): AddedOrderModel {
        return remoteSource.postOrder(order)
    }


}