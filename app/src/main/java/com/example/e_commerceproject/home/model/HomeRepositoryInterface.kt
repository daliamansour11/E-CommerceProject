package com.example.e_commerceproject.home.model

import com.example.e_commerceproject.payment.model.CouponsX
import com.example.e_commerceproject.payment.model.DiscountCode
import retrofit2.Response


interface HomeRepositoryInterface {
    suspend fun getAllBrands(): List<BrandModel>
    //suspend fun getCoupons()
}