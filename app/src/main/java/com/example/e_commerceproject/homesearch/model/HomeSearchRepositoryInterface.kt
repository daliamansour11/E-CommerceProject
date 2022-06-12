package com.example.e_commerceproject.homesearch.model

interface HomeSearchRepositoryInterface {
    suspend fun getAllProducts(): List<ProductModel>
}