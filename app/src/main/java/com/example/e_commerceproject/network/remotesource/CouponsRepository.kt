package com.example.e_commerceproject.network.remotesource

import android.util.Log
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.payment.model.CouponsX
import com.example.e_commerceproject.payment.model.DiscountCode
import retrofit2.Response

class CouponsRepository(private val retrofitService: RetrofitService) {

    //  suspend fun getCoupons() = retrofitService.getAvailableCoupons()

    suspend fun getCoupons():Response<CouponsX> {
        val coupons = retrofitService.getAvailableCoupons()
        println("-----------------------------------------------ggggggggggggggggggg")
        Log.i("TAG", "couponnnnnnnnnnnnnnnnnnnnnn: ${coupons.body()}  ${coupons.code()}")
        println("----------------------bghhghghghj-----------------")

return coupons
    }
}