package com.example.e_commerceproject.common.network

import com.example.e_commerceproject.payment.model.PostOrderModel
import com.example.e_commerceproject.homesearch.model.ProductListModel
import com.example.e_commerceproject.home.model.CollectionModel
import com.example.e_commerceproject.profile.model.OrderListModel
import retrofit2.http.*

interface APIClient {
    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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")
    @GET("smart_collections.json")
    suspend fun getAllBrandResponse(
        @Query("fields") fields: String
    ): CollectionModel

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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")
    @GET("products.json")
    suspend fun getAllProductResponse(
        @Query("fields") fields: String
    ): ProductListModel


    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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")
    @GET("customers/{customerId}/orders.json")
    suspend fun getAllOrderResponse(
        @Path("customerId") customerId: String,
        @Query("fields") fields: String
    ): OrderListModel

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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")
    @POST("orders.json")
    suspend fun postOrder(
        @Body order: PostOrderModel
    ): PostOrderModel

}