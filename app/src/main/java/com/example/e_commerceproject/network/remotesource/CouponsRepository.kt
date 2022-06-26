package com.example.e_commerceproject.network.remotesource

import android.util.Log
import com.example.e_commerceproject.payment.model.CouponsX
import retrofit2.Response

class CouponsRepository(private val retrofitService: RetrofitService){

    suspend fun getCoupons(): Response<CouponsX> {
    val coupons = retrofitService.getAvailableCoupons()
    println("------------------coupon---------------------")

    Log.i("TAG", "getcoupon: ${coupons.body()}  ${coupons.code()}")
    println("--------------------coupon-------------------")
    return  coupons
}

    suspend fun getCoupons(code: String)  = retrofitService.validateCoupons(code)

    suspend fun getPriceRuleDiscountValue(id: String) = retrofitService.getPriceRuleDiscountValue(id)
}