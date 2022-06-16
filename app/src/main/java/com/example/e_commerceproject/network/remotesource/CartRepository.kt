package com.example.e_commerceproject.network.remotesource
import android.util.Log
import com.example.e_commerceproject.cart.model.CartListModel
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder
import retrofit2.Response
class CartRepository(private val retrofitService: RetrofitService) {

    suspend fun getCartItem(): Response<CartModel> {
        val cart = retrofitService.getpostedOrder()
        println("------------------getttt---------------------")

        Log.i("TAG", "getpostitem: ${cart.body()}  ${cart.code()}")
        println("--------------------getttt-------------------")
        return cart
    }
    suspend fun updatedcartItem(draftOrder: DraftOrder): Response<CartModel> {
        val cartupdated = retrofitService.upDateCart(draftOrder)
        println("--------------------ghggggg-------------------")

        Log.i("TAG", "updatepostitem: ${cartupdated.body()}  ${cartupdated.code()}")
        println("----------------------bghhghghghj-----------------")
        return cartupdated
}}