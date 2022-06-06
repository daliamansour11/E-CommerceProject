package com.example.e_commerceproject.home.client

import com.example.e_commerceproject.home.model.ProductModel
import com.example.e_commerceproject.common.network.RetrofitAPIClient
import com.example.e_commerceproject.home.model.BrandModel

class HomeClient private constructor():HomeRemoteSource{

    companion object{
        private var instance: HomeClient? = null
        fun getInstance(): HomeClient{
            return  instance?: HomeClient()
        }
    }

    override suspend fun getAllBrandResponse(): List<BrandModel> {
        var apiClient = RetrofitAPIClient.getAPIClientInstance()
        var response = apiClient.getAllBrandResponse("id,title,image")
        return response.smart_collections
    }

    override suspend fun getAllProductResponse(): List<ProductModel> {
        var apiClient = RetrofitAPIClient.getAPIClientInstance()
        var response = apiClient.getAllProductResponse("id,title,image,variants")
        return response.products
    }
}