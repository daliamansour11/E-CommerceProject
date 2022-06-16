package com.example.e_commerceproject.home.model

import android.content.Context
import android.util.Log
import com.example.e_commerceproject.home.client.HomeClient
import com.example.e_commerceproject.home.client.HomeRemoteSource
import com.example.e_commerceproject.payment.model.CouponsX
import com.example.e_commerceproject.payment.model.DiscountCode
import retrofit2.Response

class HomeRepository private constructor(
    var remoteSource: HomeRemoteSource,
    var context: Context
) : HomeRepositoryInterface {

    companion object{
        private var instance: HomeRepository? = null
        fun getInstance(remoteSource: HomeRemoteSource  , context: Context): HomeRepository{
            return instance?: HomeRepository(
                remoteSource , context )
        }
    }

    override suspend fun getAllBrands(): List<BrandModel> {
        return remoteSource.getAllBrandResponse()
    }

//    override suspend fun getCoupons() =  remoteSource.getmyAllCoupons()

    }

