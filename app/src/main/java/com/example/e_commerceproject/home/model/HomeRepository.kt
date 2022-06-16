package com.example.e_commerceproject.home.model

import android.content.Context
import com.example.e_commerceproject.home.client.HomeRemoteSource

class HomeRepository private constructor(
    var remoteSource: HomeRemoteSource,
    var context: Context
) : HomeRepositoryInterface {

    companion object{
        private var instance: HomeRepository? = null
        fun getInstance(remoteSource: HomeRemoteSource, context: Context): HomeRepository{
            return instance?: HomeRepository(
                remoteSource, context)
        }
    }

    override suspend fun getAllBrands(): List<BrandModel> {
        return remoteSource.getAllBrandResponse()
    }

}