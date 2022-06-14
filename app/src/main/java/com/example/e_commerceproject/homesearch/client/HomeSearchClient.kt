package com.example.e_commerceproject.homesearch.client

import com.example.e_commerceproject.homesearch.model.ProductModel
import com.example.e_commerceproject.common.network.RetrofitAPIClient

class HomeSearchClient private constructor():HomeSearchRemoteSource{

    companion object{
        private var instance: HomeSearchClient? = null
        fun getInstance(): HomeSearchClient{
            return  instance?: HomeSearchClient()
        }
    }


    override suspend fun getAllProductResponse(): List<ProductModel> {
        var apiClient = RetrofitAPIClient.getAPIClientInstance()
        var response = apiClient.getAllProductResponse("id,title,image,variants")
        return response.products
    }
}