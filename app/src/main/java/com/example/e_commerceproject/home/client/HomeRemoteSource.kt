package com.example.e_commerceproject.home.client

import com.example.e_commerceproject.home.model.BrandModel
import com.example.e_commerceproject.payment.model.CouponsX
import com.example.e_commerceproject.payment.model.DiscountCode
import retrofit2.Response

interface HomeRemoteSource {

   suspend fun getAllBrandResponse(): List<BrandModel>
 // suspend fun  getmyAllCoupons()
}