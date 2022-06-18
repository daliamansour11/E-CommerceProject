package com.example.e_commerceproject.home.model

import com.example.e_commerceproject.home.client.HomeRemoteSource

class FakeDataSource() : HomeRemoteSource {

    private val brand1 = BrandModel(1,"Zara", BrandImageModel("ZaraImg",10,10))
    private val brand2 = BrandModel(2,"Puma",BrandImageModel("PumaImg",10,10))
    private val brand3 = BrandModel(3,"Adidas",BrandImageModel("AdidasImg",10,10))
    private val remoteBrands = listOf(brand1, brand2,brand3)
    override suspend fun getAllBrandResponse(): List<BrandModel> {
        remoteBrands.let {
           return ArrayList(it)
        }
        throw Exception("Not Brands found")
    }
}