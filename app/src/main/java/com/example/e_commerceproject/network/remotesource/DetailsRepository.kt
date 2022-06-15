package com.example.e_commerceproject.network.remotesource

import android.util.Log
import com.example.e_commerceproject.cart.model.CartListModel
import com.example.e_commerceproject.cart.model.CartModel
import retrofit2.Response

class DetailsRepository ( private  val retrofitService: RetrofitService) {
    suspend fun getProductInf(product_id: String) = retrofitService.getProductInfo(product_id)
    suspend fun postCartItem(cartItem: CartModel) : Response<CartModel> {

        val n = retrofitService.postCartOrder(cartItem)
        Log.i("TAG", "postCartItemmmmmmmmmmmmmmmmmmmmmmmmmmmmm: ${n.body()}  ${n.code()}")
        return n
    }
}