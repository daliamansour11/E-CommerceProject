package com.example.e_commerceproject.home.client

import android.util.Log
import com.example.e_commerceproject.common.network.RetrofitAPIClient
import com.example.e_commerceproject.home.model.BrandModel
import com.example.e_commerceproject.payment.model.CouponsX
import com.example.e_commerceproject.payment.model.DiscountCode
import retrofit2.Response
import retrofit2.adapter.rxjava.Result.response

class HomeClient private constructor():HomeRemoteSource {

    companion object {
        private var instance: HomeClient? = null
        fun getInstance(): HomeClient {
            return instance ?: HomeClient()
        }
    }

    override suspend fun getAllBrandResponse(): List<BrandModel> {
        var apiClient = RetrofitAPIClient.getAPIClientInstance()
        var response = apiClient.getAllBrandResponse("id,title,image")
        return response.smart_collections
    }
//    override suspend fun getmyAllCoupons() {
//        var apiClient = RetrofitAPIClient.getAPIClientInstance()
//        var response = apiClient.getAvailableCoupons()
//        println("------------------coupon---------------------")
//
//        //return  listOf(response)
//        println("------------------coupon---------------------")
//
//
//
//    }
}