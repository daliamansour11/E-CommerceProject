package com.example.e_commerceproject.common.network

import com.example.e_commerceproject.home.model.ProductListModel
import com.example.e_commerceproject.home.model.CollectionModel
import com.example.e_commerceproject.profile.model.OrderListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {
    @GET("smart_collections.json")
    suspend fun getAllBrandResponse(
        @Query("fields") fields: String
    ): CollectionModel

    @GET("products.json")
    suspend fun getAllProductResponse(
        @Query("fields") fields: String
    ): ProductListModel

    @GET("orders.json")
    suspend fun getAllOrderResponse(
        @Query("fields") fields: String
    ): OrderListModel

}