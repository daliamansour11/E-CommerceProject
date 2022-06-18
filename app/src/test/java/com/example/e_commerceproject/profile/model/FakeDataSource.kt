package com.example.e_commerceproject.profile.model


import com.example.e_commerceproject.profile.client.ProfileRemoteSource

class FakeDataSource(): ProfileRemoteSource {


    private val order1 = OrderModel(1,"100","18/6/2022", CustomerModel("Ahmed","Mohamed"), listOf(
        LineItemModel("T-Shirt","15",5)
    ))

    private val order2 =OrderModel(2,"200","18/6/2022", CustomerModel("Adam","Mohamed"), listOf(LineItemModel("T-Shirt","15",5)))
    private val order3 = OrderModel(3,"300","18/6/2022", CustomerModel("Ali","Mohamed"), listOf(LineItemModel("T-Shirt","25",10)))

    private val remoteAllOrders = listOf(order1, order2,order3)

    override suspend fun getAllOrders(): List<OrderModel> {
        remoteAllOrders.let {
            return ArrayList(it)
        }
        throw Exception("Not Orders found")
    }

}