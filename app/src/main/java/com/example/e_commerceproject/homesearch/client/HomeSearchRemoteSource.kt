package com.example.e_commerceproject.homesearch.client

import com.example.e_commerceproject.homesearch.model.ProductModel

interface HomeSearchRemoteSource {

   suspend fun getAllProductResponse(): List<ProductModel>
}