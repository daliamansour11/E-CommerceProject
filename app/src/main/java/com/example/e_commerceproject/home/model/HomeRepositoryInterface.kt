package com.example.e_commerceproject.home.model


interface HomeRepositoryInterface {
    suspend fun getAllBrands(): List<BrandModel>
}