package com.example.e_commerceproject.common.network

import com.example.e_commerceproject.home.model.CollectionModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {
    @GET("smart_collections.json")
    suspend fun getAllBrandResponse(
        @Query("fields") fields: String
    ): CollectionModel

}