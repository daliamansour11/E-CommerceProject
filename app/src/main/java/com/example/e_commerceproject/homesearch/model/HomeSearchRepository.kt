package com.example.e_commerceproject.homesearch.model

import android.content.Context
import com.example.e_commerceproject.homesearch.client.HomeSearchRemoteSource

class HomeSearchRepository private constructor(
    var remoteSource: HomeSearchRemoteSource,
    var context: Context
) : HomeSearchRepositoryInterface {

    companion object{
        private var instance: HomeSearchRepository? = null
        fun getInstance(remoteSource: HomeSearchRemoteSource, context: Context): HomeSearchRepository{
            return instance?: HomeSearchRepository(
                remoteSource, context)
        }
    }

    override suspend fun getAllProducts(): List<ProductModel> {
        return remoteSource.getAllProductResponse()
    }
}